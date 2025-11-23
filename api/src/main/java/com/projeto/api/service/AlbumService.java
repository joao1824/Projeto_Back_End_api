package com.projeto.api.service;

// spotify-web-api-java
import com.projeto.api.client.SpotifyClient;
import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.AlbumDTOs.AlbumFilter;
import com.projeto.api.exception.exceptions.*;
import com.projeto.api.mapper.dtos.AlbumMapper;
import com.projeto.api.models.Album;
import com.projeto.api.models.Artista;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.repository.ArtistaRepository;
import com.projeto.api.specification.AlbumSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AlbumService {

    // importar access token
    private final SpotifyApi spotifyApi;
    private final AlbumRepository albumRepository;
    private final ArtistaRepository artistaRepository;
    private final ArtistaService artistaService;
    private final AlbumMapper albumMapper;


    public AlbumService(AlbumMapper albumMapper ,SpotifyClient spotifyClient, AlbumRepository albumRepository, ArtistaRepository artistaRepository, ArtistaService artistaService) {
        this.spotifyApi = spotifyClient.getApi();
        this.albumRepository = albumRepository;
        this.artistaRepository = artistaRepository;
        this.artistaService = artistaService;
        this.albumMapper = albumMapper;
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
                throw new EmptyException("Nenhum album encontrado com o nome: " + nome);
            }
            return mapApiParaAlbum(apiAlbum[0]); // mapApiParaAlbum(apiAlbum[0])
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao buscar Album: " + e.getMessage());
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
    public Page<AlbumDTO> getAllAlbums(AlbumFilter filtros, Pageable pageable) {
        Specification<Album> specification = AlbumSpecification.filtrosAplicados(filtros);
        Page<Album> albums = albumRepository.findAll(specification, pageable);
        return albums.map(albumMapper::toAlbumDTO);
    }

    //Retorna por id
    public AlbumDTO getAlbumById(String id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException("Album não encontrado com o ID: " + id));
        return albumMapper.toAlbumDTO(album);
    }

    //cria novo album
    public AlbumDTO novoAlbum(AlbumDTO albumDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        Album album = new Album();
        album.setNome(albumDTO.getNome());
        album.setPopularidade(albumDTO.getPopularidade());
        album.setLancamento(albumDTO.getLancamento());
        album.setGravadora(albumDTO.getGravadora());
        album.setPerfil_spotify(albumDTO.getPerfil_spotify());
        album.setTotal_faixas(albumDTO.getTotal_faixas());
        album.setNota_media(null);
        album.setImagens(albumDTO.getImagens());
        album.setGeneros(albumDTO.getGeneros());


        List<Artista> artistas = albumDTO.getArtistas().stream().map(artista -> {
            return artistaRepository.findById(artista.getId()).orElseThrow(() -> new ArtistaNotFoundException("Artista não encontrado com o ID: " + artista.getId()));
        }).toList();

        album.setArtistas(artistas);
        albumRepository.save(album);
        return albumMapper.toAlbumDTO(album);
    }

    //Atualiza album
    public AlbumDTO atualizarAlbum(String id, AlbumDTO albumDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }



        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException("Album não encontrado com o ID: " + id));

        List<Artista> artistas = new ArrayList<>(albumDTO.getArtistas().stream().map(artista -> {
            return artistaRepository.findById(artista.getId()).orElseThrow(() -> new ArtistaNotFoundException("Artista não encontrado com o ID: " + artista.getId()));
        }).toList()) ;


        album.setArtistas(artistas);

        album.setNome(albumDTO.getNome());
        album.setPopularidade(albumDTO.getPopularidade());
        album.setLancamento(albumDTO.getLancamento());
        album.setGravadora(albumDTO.getGravadora());
        album.setGeneros(albumDTO.getGeneros());
        album.setPerfil_spotify(albumDTO.getPerfil_spotify());
        album.setTotal_faixas(albumDTO.getTotal_faixas());
        album.setImagens(albumDTO.getImagens());
        albumRepository.save(album);
        return albumMapper.toAlbumDTO(album);
    }

    // Deleta um album por ID
    public void deletarAlbum(String id) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }


        Album album = albumRepository.findById(id).orElseThrow(() -> new AlbumNotFoundException("Album não encontrado com o ID: " + id));
        albumRepository.delete(album);
    }

    private LocalDate parseSpotifyDate(String dateString) {
        try {
            if (dateString.length() == 4) {
                return LocalDate.of(Integer.parseInt(dateString), 1, 1);
            } else if (dateString.length() == 7) {
                return LocalDate.parse(dateString + "-01");
            } else {
                return LocalDate.parse(dateString);
            }
        } catch (Exception e) {
            throw new ExternalApiException("Erro ao converter Spotify date: " + e.getMessage());
        }
    }

    public Album getOrCreateAlbum(String nomeAlbum, String nomeArtista) {
        Artista artista = artistaRepository.findByNome(nomeArtista)
                .orElseGet(() -> artistaService.getOrCreateArtista(nomeArtista));

        Optional<Album> albumExistente = albumRepository.findByNomeAndArtistas_Nome(nomeAlbum, nomeArtista);
        if (albumExistente.isPresent()) {
            return albumExistente.get();
        }

        try {
            AlbumSimplified[] resultados = spotifyApi.searchAlbums(nomeAlbum)
                    .limit(1)
                    .build()
                    .execute()
                    .getItems();

            if (resultados.length == 0) {
                throw new ExternalApiException("Álbum não encontrado no Spotify");
            }

            se.michaelthelin.spotify.model_objects.specification.Album spotifyAlbum =
                    spotifyApi.getAlbum(resultados[0].getId()).build().execute();


            Album novoAlbum = new Album();
            novoAlbum.setNome(spotifyAlbum.getName());
            novoAlbum.setLancamento(parseSpotifyDate(spotifyAlbum.getReleaseDate()));
            novoAlbum.setPerfil_spotify(spotifyAlbum.getExternalUrls().get("spotify"));
            novoAlbum.setNota_media(null);
            novoAlbum.setTotal_faixas(spotifyAlbum.getTracks().getTotal());
            novoAlbum.setGravadora(spotifyAlbum.getLabel());
            novoAlbum.setArtistas(Collections.singletonList(artista));

            return albumRepository.save(novoAlbum);

        } catch (Exception e) {
            throw new ExternalApiException("Erro ao buscar álbum no Spotify: " + e.getMessage());
        }
    }




}
