package com.projeto.api.service;

import com.projeto.api.dtos.ArtistasDTOs.ArtistaDTO;
import com.projeto.api.exception.exceptions.*;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.ArtistaRepository;
import com.projeto.api.util.IdGerador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.projeto.api.client.SpotifyClient;
import com.projeto.api.models.Artista;

// spotify-web-api-java
import org.springframework.web.server.ResponseStatusException;
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
    private final ArtistaRepository artistaRepository;

    public ArtistaService(SpotifyClient spotifyClient, ArtistaRepository artistaRepository) {
        this.spotifyApi = spotifyClient.getApi();
        this.artistaRepository = artistaRepository;
    }

    public Artista buscarArtista(String nome) {
        try {
            Artist[] apiArtistas = spotifyApi.searchArtists(nome)
                                             .limit(1)
                                             .build()
                                             .execute()
                                             .getItems();

            if (apiArtistas.length == 0) {
                throw new EmptyException("Artista não encontrado na API do Spotify");
            }
            return mapApiParaArtista(apiArtistas[0]);
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao buscar artista: " + e.getMessage());
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

    // procurar artista requisitado (se nao tiver cadastrado no banco, cadastrar
    public Artista getOrCreateArtista(String nome) {
        var artistaExistente = artistaRepository.findByNome(nome);
        if (artistaExistente.isPresent()) {
            return artistaExistente.get();
        }

        Artista spotifyArtista = buscarArtista(nome);
        if (spotifyArtista == null) {
            throw new RuntimeException("Artista não encontrado no Spotify");
        }

        IdGerador gerador = new IdGerador();
        spotifyArtista.setId(gerador.Gerar()); // revisar

        return artistaRepository.save(spotifyArtista);
    }

    // Retorna todos os artistas com paginação
    public Page<ArtistaDTO> getAllArtistas(Pageable pageable) {
          Page<Artista> Artistas = artistaRepository.findAll(pageable);

          return Artistas.map(ArtistaDTO::new);
    }

    // Retorna um artista por ID
    public ArtistaDTO getArtistaById(String id) {
        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new ArtistaNotFoundException("Artista não encontrado com o ID: " + id));
        return new ArtistaDTO(artista);
    }

    public ArtistaDTO novoArtista(ArtistaDTO artistaDTO) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        Artista artista = new Artista();
        artista.setNome(artistaDTO.getNome());
        artista.setPopularidade(artistaDTO.getPopularidade());
        artista.setSeguidores(artistaDTO.getSeguidores());
        artista.setPerfil_spotify(artistaDTO.getPerfil_spotify());
        artista.setGeneros(artistaDTO.getGeneros());
        artista.setImagem(artistaDTO.getImagem());
        artistaRepository.save(artista);
        return new ArtistaDTO(artista);
    }

    public ArtistaDTO atualizarArtista(String id, ArtistaDTO artistaDTO) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }


        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new ArtistaNotFoundException("Artista não encontrado com o ID: " + id));
        artista.setNome(artistaDTO.getNome());
        artista.setPopularidade(artistaDTO.getPopularidade());
        artista.setSeguidores(artistaDTO.getSeguidores());
        artista.setPerfil_spotify(artistaDTO.getPerfil_spotify());
        artista.setGeneros(artistaDTO.getGeneros());
        artista.setImagem(artistaDTO.getImagem());
        artistaRepository.save(artista);
        return new ArtistaDTO(artista);


    }


    public void deletarArtista(String id) {
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        Artista artista = artistaRepository.findById(id).orElseThrow(() -> new ArtistaNotFoundException("Artista não encontrado com o ID: " + id));
        artistaRepository.delete(artista);
    }
}

