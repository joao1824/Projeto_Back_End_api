package com.projeto.api.service;

// spotify-web-api-java
import com.projeto.api.client.SpotifyClient;
import com.projeto.api.dtos.AlbumDTO;
import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.repository.ArtistaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AlbumService {

    // importar access token
    private final SpotifyApi spotifyApi;
    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;
    private final ArtistaService artistaService;


    public AlbumService(SpotifyClient spotifyClient, AlbumRepository albumRepository, ArtistaRepository artistaRepository, ArtistaService artistaService) {
        this.spotifyApi = spotifyClient.getApi();
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
        this.artistaService = artistaService;
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

    //Retorna todos os albums com paginação
    public Page<AlbumDTO> getAllAlbums(Pageable pageable) {
        Page<Album> albums = albumRepository.findAll(pageable);

        if (albums.isEmpty()) {
            throw new RuntimeException("Nenhum album encontrado.");
        }
        return albums.map(AlbumDTO::new);
    }

    //Retorna por id
    public AlbumDTO getAlbumById(String id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum album encontrado."));
        return new AlbumDTO(album);
    }

    //cria novo album
    public AlbumDTO novoAlbum(AlbumDTO albumDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem criar tags.");
        }

        Album album = new Album();
        album.setNome(albumDTO.getNome());
        album.setPopularidade(albumDTO.getPopularidade());
        album.setLancamento(albumDTO.getLancamento());
        album.setGravadora(albumDTO.getGravadora());
        album.setPerfil_spotify(albumDTO.getPerfil_spotify());
        album.setTotal_faixas(albumDTO.getTotal_faixas());
        album.setNota_media(0);
        album.setImagens(albumDTO.getImagens());
        album.setGeneros(albumDTO.getGeneros());


        List<Artista> artistas = albumDTO.getArtistas().stream().map(artista -> {
            return artistaRepository.findById(artista.getId()).orElseThrow(() -> new RuntimeException("Artista com id " + artista.getId() + " não encontrado."));
        }).toList();

        album.setArtistas(artistas);
        albumRepository.save(album);
        return new AlbumDTO(album);
    }

    //Atualiza album
    public AlbumDTO atualizarAlbum(String id, AlbumDTO albumDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem criar tags.");
        }



        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum album encontrado."));

        List<Artista> artistas = new ArrayList<>(albumDTO.getArtistas().stream().map(artista -> {
            return artistaRepository.findById(artista.getId()).orElseThrow(() -> new RuntimeException("Artista com id " + artista.getId() + " não encontrado."));
        }).toList()) ;


        album.setArtistas(artistas);

        album.setNome(albumDTO.getNome());
        album.setPopularidade(albumDTO.getPopularidade());
        album.setLancamento(albumDTO.getLancamento());
        album.setGravadora(albumDTO.getGravadora());
        album.setPerfil_spotify(albumDTO.getPerfil_spotify());
        album.setTotal_faixas(albumDTO.getTotal_faixas());
        album.setImagens(albumDTO.getImagens());
        albumRepository.save(album);
        return new AlbumDTO(album);
    }

    // Deleta um album por ID
    public void deletarAlbum(String id) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Apenas administradores podem criar tags.");
        }


        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum album encontrado."));
        albumRepository.delete(album);
    }

    public Album getOrCreateAlbum(String nomeAlbum, String nomeArtista) {
        Optional<Album> albumExistente = albumRepository.findByNome(nomeAlbum);
        if (albumExistente.isPresent()) {
            return albumExistente.get();
        }

        Artista artista = artistaRepository.findByNome(nomeArtista)
                .orElseGet(() -> artistaService.getOrCreateArtista(nomeArtista));

        AlbumSimplified[] apiAlbums;
        try {
            apiAlbums = spotifyApi.searchAlbums(nomeAlbum)
                    .limit(1)
                    .build()
                    .execute()
                    .getItems();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar álbum no Spotify: " + e.getMessage(), e);
        }

        if (apiAlbums.length == 0) {
            throw new RuntimeException("Álbum não encontrado no Spotify");
        }

        AlbumSimplified spotifyAlbum = apiAlbums[0];

        Album album = new Album();
        album.setNome(spotifyAlbum.getName());
        //album.setTotal_faixas(spotifyAlbum.getTotalTracks());
        //album.setLancamento(spotifyAlbum.getReleaseDate());
        album.setPerfil_spotify(spotifyAlbum.getExternalUrls().get("spotify"));
        //album.setArtistas(Collections.singletonList(artista));
        album.setNota_media(0);

        return albumRepository.save(album);
    }

}
