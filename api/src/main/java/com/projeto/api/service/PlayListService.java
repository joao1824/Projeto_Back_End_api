package com.projeto.api.service;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.dtos.MusicaDTOs.MusicaResumoDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.exception.exceptions.*;
import com.projeto.api.mapper.dtos.PlayListMapper;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.MusicaRepository;
import com.projeto.api.repository.PlayListRepository;
import com.projeto.api.specification.PlayListSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    private final PlayListRepository playListRepository;
    private final MusicaRepository musicaRepository;
    private final PlayListMapper playListMapper;

    public PlayListService(PlayListRepository playListRepository, MusicaRepository musicaRepository, PlayListMapper playListMapper) {
        this.playListRepository = playListRepository;
        this.musicaRepository = musicaRepository;
        this.playListMapper = playListMapper;
    }

    // Retorna todos
    public Page<PlayListDTO> getAllPlaylist(Map<String,String> filtros, Pageable pageable) {
        Specification<PlayList> specification = PlayListSpecification.aplicarFiltros(filtros);
        Page<PlayList> playlists = playListRepository.findAll(specification,pageable);

        return playlists.map(playListMapper::toPlayListDTO);
    }


    //buscar por id

    public PlayListDTO getPlayListById(String id){
        PlayList playlist = playListRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException("PlayListSpecification não encontrada: "+ id));
        return playListMapper.toPlayListDTO(playlist);
    }

    // Adiciona um novo
    public PlayListDTO novaPlaylist(PlayListDTO data){

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        PlayList playlist = new PlayList();

        playlist.setNome(data.getNome());
        playlist.setUsuario(usuarioLogado);
        playlist.setDescricao(data.getDescricao());

        List<String> musicaIds = data.getMusicas().stream()
                .map(MusicaResumoDTO::getId)
                .collect(Collectors.toList());

        List<Musica> musicas = musicaRepository.findAllById(musicaIds);

        Set<String> encontrados = musicas.stream()
                .map(Musica::getId)
                .collect(Collectors.toSet());

        List<String> naoEncontrados = musicaIds.stream()
                .filter(id -> !encontrados.contains(id))
                .toList();

        if (!naoEncontrados.isEmpty()) {
            throw new MusicaNotFoundException("Músicas não encontradas: " + naoEncontrados);
        }

        playlist.setMusicas(musicas);
        playListRepository.save(playlist);

        return playListMapper.toPlayListDTO(playlist);
    }

    //Update
    public PlayListDTO update(String id_playlist, PlayListDTO data){

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        PlayList playlist = playListRepository.findById(id_playlist).orElseThrow(() -> new PlaylistNotFoundException("PlayListSpecification não encontrada: "+ id_playlist));

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playlist.getUsuario().getId())) {
            throw new UserNotAdminException("Usuário " + usuarioLogado.getEmail() + " não é administrador da playlist");
        }

        playlist.setNome(data.getNome());
        playlist.setDescricao(data.getDescricao());
        List<String> musicaIds = data.getMusicas().stream()
                .map(MusicaResumoDTO::getId)
                .collect(Collectors.toList());

        List<Musica> musicas = musicaRepository.findAllById(musicaIds);

        Set<String> encontrados = musicas.stream()
                .map(Musica::getId)
                .collect(Collectors.toSet());

        List<String> naoEncontrados = musicaIds.stream()
                .filter(id -> !encontrados.contains(id))
                .toList();

        if (!naoEncontrados.isEmpty()) {
            throw new MusicaNotFoundException("Músicas não encontradas: " + naoEncontrados);
        }

        playlist.setMusicas(musicas);

        playListRepository.save(playlist);

        return playListMapper.toPlayListDTO(playlist);
    }

    //Deletar

    public void delete(String id){
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        PlayList playlist = playListRepository.findById(id).orElseThrow(() -> new PlaylistNotFoundException("PlayListSpecification não encontrada: "+ id));

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playlist.getUsuario().getId())) {
            throw new UserNotAdminException("Usuário " + usuarioLogado.getEmail() + " não é administrador da playlist");
        }

        playListRepository.delete(playlist);
    }



    //adicionar musica

    public PlayListDTO addMusica(String id_playlist, String id_musica){
        PlayList playList  = playListRepository.findById(id_playlist).orElseThrow(() -> new PlaylistNotFoundException("PlayListSpecification não encontrada: "+ id_playlist));

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playList.getUsuario().getId())) {
            throw new UserNotAdminException("Usuário " + usuarioLogado.getEmail() + " não é administrador da playlist");
        }

        if (playList.getMusicas().stream().anyMatch(musica -> musica.getId().equals(id_musica))) {
            throw new ReapetedMusicException();
        }

        Musica musica = musicaRepository.findById(id_musica).orElseThrow(() -> new MusicaNotFoundException("Música não encontrada: "+ id_musica));
        playList.addMusica(musica);
        playListRepository.save(playList);

        return playListMapper.toPlayListDTO(playList);
    }

    //remover musica

    public void removeMusica(String id_playlist, String id_musica){

        PlayList playList  = playListRepository.findById(id_playlist).orElseThrow(() -> new PlaylistNotFoundException("PlayListSpecification não encontrada: "+ id_playlist));
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playList.getUsuario().getId())) {
            throw new UserNotAdminException("Usuário " + usuarioLogado.getEmail() + " não é administrador da playlist");
        }


        Musica musica = musicaRepository.findById(id_musica).orElseThrow(() -> new MusicaNotFoundException("Música não encontrada: "+ id_musica));
        playList.removeMusica(musica);
        playListRepository.save(playList);
    }




}
