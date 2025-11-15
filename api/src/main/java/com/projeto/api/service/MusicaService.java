package com.projeto.api.service;

import com.projeto.api.client.SpotifyClient;
import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.exception.exceptions.EventIdNotFoundException;
import com.projeto.api.exception.exceptions.ExternalApiException;
import com.projeto.api.exception.exceptions.MusicaNotFoundException;
import com.projeto.api.exception.exceptions.UserNotAdminException;
import com.projeto.api.models.Album;
import com.projeto.api.models.Musica;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.repository.MusicaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.specification.Track;

@Service
public class MusicaService {

    private final SpotifyApi spotifyApi;
    private final AlbumService albumService;
    private final MusicaRepository musicaRepository;
    private final AlbumRepository albumRepository;

    public MusicaService(AlbumRepository albumRepository, MusicaRepository musicaRepository, SpotifyClient spotifyClient, AlbumService albumService) {
        this.spotifyApi = spotifyClient.getApi();
        this.albumService = albumService;
        this.musicaRepository = musicaRepository;
        this.albumRepository = albumRepository;
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
            throw new ExternalApiException("Erro ao buscar músicas: " + e.getMessage());
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

    // Retorna todas as músicas com paginação
    public Page<MusicaDTO> getAllMusicas(Pageable pageable) {

        Page<Musica> musicas = musicaRepository.findAll(pageable);
        return musicas.map(MusicaDTO::new);
    }

    // Retorna uma música por ID
    public MusicaDTO getMusicaById(String id) {
        Musica musica = musicaRepository.findById(id).orElseThrow(() -> new MusicaNotFoundException("Música com ID " + id + " não encontrada."));
        return new MusicaDTO(musica);
    }

    // Cria uma nova música

    public MusicaDTO novaMusica(MusicaDTO musicaDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }

        Musica musica = new Musica();
        musica.setNome(musicaDTO.getNome());
        musica.setDuracao(musicaDTO.getDuracao());
        musica.setExplicito(musicaDTO.isExplicito());
        musica.setFaixa_numero(musicaDTO.getFaixa_numero());
        musica.setPerfil_spotify(musicaDTO.getPerfil_spotify());

        Album album = albumRepository.findById(musicaDTO.getAlbum().getId()).orElseThrow(() -> new EventIdNotFoundException("Album com ID " + musicaDTO.getAlbum().getId() + " não encontrado."));

        musica.setAlbum(album);

        musicaRepository.save(musica);
        return new MusicaDTO(musica);
    }

    // Atualiza uma música existente
    public MusicaDTO atualizarMusica(String id, MusicaDTO musicaDTO) {

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }


        Musica musica = musicaRepository.findById(id).orElseThrow(EventIdNotFoundException::new);
        musica.setNome(musicaDTO.getNome());
        musica.setDuracao(musicaDTO.getDuracao());
        musica.setExplicito(musicaDTO.isExplicito());
        musica.setFaixa_numero(musicaDTO.getFaixa_numero());
        musica.setPerfil_spotify(musicaDTO.getPerfil_spotify());

        Album album = albumRepository.findById(musicaDTO.getAlbum().getId()).orElseThrow(() -> new EventIdNotFoundException("Album com ID " + musicaDTO.getAlbum().getId() + " não encontrado."));
        musica.setAlbum(album);
        musicaRepository.save(musica);
        return new MusicaDTO(musica);
    }


    public void deletarMusica(String id) {


        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // verifica se é admin
        if (!usuarioLogado.getIsAdmin()) {
            throw new UserNotAdminException();
        }


        Musica musica = musicaRepository.findById(id).orElseThrow(() -> new MusicaNotFoundException("Música com ID " + id + " não encontrada."));
        musicaRepository.delete(musica);
    }


}
