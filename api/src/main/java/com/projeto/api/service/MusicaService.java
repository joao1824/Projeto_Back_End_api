package com.projeto.api.service;

import com.projeto.api.client.SpotifyClient;
import com.projeto.api.models.Musica;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;

@Service
public class MusicaService {

    private final SpotifyApi spotifyApi;
    private final AlbumService albumService;

    public MusicaService(SpotifyClient spotifyClient, AlbumService albumService) {
        this.spotifyApi = spotifyClient.getApi();
        this.albumService = albumService;
    }

    public Musica buscarMusica(String nome) {
        try {
            Track[] track = spotifyApi.searchTracks(nome)
                    .limit(1)
                    .build()
                    .execute()
                    .getItems();

            return mapApiParaMusica(track[0]);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar músicas: " + e.getMessage(), e);
        }
    }

    public Musica mapApiParaMusica(Track track) {
        return new Musica(
                track.getName(),
                track.getDurationMs(),
                track.getIsExplicit(),
                track.getTrackNumber(),
                "",
                albumService.buscarAlbum(track.getAlbum().getName())
        );
    }
}
