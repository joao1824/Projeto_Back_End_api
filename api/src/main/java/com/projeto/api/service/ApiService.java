package com.projeto.api.service;

// dependencia spotify-web-api-java
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Album;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.projeto.api.models.Artista;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ApiService {

    private final SpotifyApi spotifyApi;

    public ApiService(

            // pegar variaveis da env
            @Value("${spotify.client.id}")
            String clientId,
            @Value("${spotify.client.secret}")
            String clientSecret

    ) {
        // configura spotifyAPI com dependencia
        this.spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        try {
            // cria accesstoken especifico com clientid e clientsecret
            var clientCredentials = spotifyApi.clientCredentials().build().execute();
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar Access Token: " + e.getMessage(), e);
        }
    }

    // GET ARTISTA usando dependencia
    public Artista[] buscarArtista(String nome) {
        try {
            Artist[] spotifyArtists = spotifyApi.searchArtists(nome).limit(5).build().execute().getItems();

            return Arrays.stream(spotifyArtists)
                    .map(this::mapApiParaArtista)
                    .toArray(Artista[]::new);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar artista: " + e.getMessage(), e);
        }
    }

    public Artista mapApiParaArtista(Artist apiArtista) {
        // pegar e listarimagens
        List<String> imagens = Arrays.stream(apiArtista.getImages())
                                     .map(img -> img.getUrl())
                                     .collect(Collectors.toList());

        // listar generos
        List<String> generos = Arrays.asList(apiArtista.getGenres());

        // albuns
        GetArtistsAlbumsRequest albuns = spotifyApi.getArtistsAlbums(apiArtista.getId())
                .limit(5)
                .build();

        return new Artista(
                apiArtista.getName(),
                apiArtista.getPopularity(),
                apiArtista.getFollowers().getTotal(),
                apiArtista.getExternalUrls().get("spotify"),
                imagens,
                generos,
                Collections.emptyList()
        );

    }

    // GET ALBUM usando dependencia
    // simplified pq a busca funciona de modo diferente
    public AlbumSimplified[] buscarAlbum(String nome) {
        try {
            SearchAlbumsRequest request = spotifyApi.searchAlbums(nome).limit(5).build();
            return request.execute().getItems();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar álbum: " + e.getMessage(), e);
        }
    }
}
