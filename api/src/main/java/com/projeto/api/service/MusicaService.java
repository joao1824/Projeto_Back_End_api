package com.projeto.api.service;

import com.projeto.api.client.SpotifyClient;
import com.projeto.api.models.Letra;
import com.projeto.api.models.Musica;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    private final SpotifyApi spotifyApi;
    private final AlbumService albumService;

    public MusicaService(SpotifyClient spotifyClient, AlbumService albumService) {
        this.spotifyApi = spotifyClient.getApi();
        this.albumService = albumService;
    }

    public List<Musica> buscarMusicas(String nome) {
        try {
            Track[] tracks = spotifyApi.searchTracks(nome)
                    .limit(3)
                    .build()
                    .execute()
                    .getItems();

            return Arrays.stream(tracks)
                    .map(this::mapTrackToMusica)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar músicas: " + e.getMessage(), e);
        }
    }

    private Musica mapApiParaMusica(Track track) {
        return new Musica(
                track.getName(),
                track.getDurationMs(),
                track.getIsExplicit(),
                track.getTrackNumber(),
                "",
                albumService.buscarAlbum(track.getName()),



        );
    }
}
