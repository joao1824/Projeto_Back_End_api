package com.projeto.api.service;

import org.springframework.stereotype.Service;

import com.projeto.api.client.SpotifyClient;
import com.projeto.api.models.Artista;

// spotify-web-api-java
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Image;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    private final SpotifyApi spotifyApi;

    public ArtistaService(SpotifyClient spotifyClient) {
        this.spotifyApi = spotifyClient.getApi();
    }

    public Artista buscarArtista(String nome) {
        try {
            Artist[] apiArtistas = spotifyApi.searchArtists(nome)
                                             .limit(1)
                                             .build()
                                             .execute()
                                             .getItems();

            if (apiArtistas.length == 0) {
                return null;
            }
            return mapApiParaArtista(apiArtistas[0]);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar artista: " + e.getMessage(), e);
        }
    }

    public Artista mapApiParaArtista(Artist apiArtista) {
        // pegar e listarimagens
        List<String> imagens = Arrays.stream(apiArtista.getImages())
                .map(Image::getUrl)
                .collect(Collectors.toList());
        String imagem = imagens.get(0);

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
                generos,
                imagem,
                Collections.emptyList() // A FAZER: MOSTRAR ALBUNS DO ARTISTA
        );

    }

}
