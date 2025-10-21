package com.projeto.api.service;

// spotify-web-api-java
import com.projeto.api.client.SpotifyClient;
import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;

import org.springframework.stereotype.Service;

@Service
public class AlbumService {

    // importar access token
    private final SpotifyApi spotifyApi;

    public AlbumService(SpotifyClient spotifyClient) {
        this.spotifyApi = spotifyClient.getApi();
    }

    // GET ALBUM usando dependencia
    // simplified pq a busca funciona de modo diferente
    public Album buscarAlbum(String nome) {
        try {
            AlbumSimplified[] apiAlbum = spotifyApi.searchAlbums(nome)
                                                      .limit(1)
                                                      .build()
                                                      .execute()
                                                      .getItems();
            if (apiAlbum.length == 0) {
                return null;
            }
            return mapApiParaAlbum(apiAlbum[0]); // mapApiParaAlbum(apiAlbum[0])
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar artista: " + e.getMessage(), e);
        }
    }

    public Album mapApiParaAlbum(AlbumSimplified apiAlbum) {
        return new Album(
                apiAlbum.getName(),
                0,
                apiAlbum.getReleaseDate(),
                "", // A FAZER: transformar de AlbumSimplified em Album p pegar label etc
                apiAlbum.getExternalUrls().get("spotify"),
                0

        );
    }

}
