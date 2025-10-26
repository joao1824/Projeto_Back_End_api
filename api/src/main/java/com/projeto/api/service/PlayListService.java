package com.projeto.api.service;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlaylistAddDeleteMusicaDTO;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.MusicaRepository;
import com.projeto.api.repository.PlayListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    private final PlayListRepository playListRepository;
    private final MusicaRepository musicaRepository;

    public PlayListService(PlayListRepository playListRepository, MusicaRepository musicaRepository) {
        this.playListRepository = playListRepository;
        this.musicaRepository = musicaRepository;
    }

    //Retorna todos
    public List<PlayListDTO> getAll() {
        List<PlayList> playlist = playListRepository.findAll();

        if (playlist.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum playlist encontrada ); .");
        }
        return playlist.stream().map(PlayListDTO::new).collect(Collectors.toList());
    }

    //Adiciona um novo
    public ResponseEntity<String> novaPlaylist(PlayListDTO data){

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        PlayList playlist = new PlayList();

        playlist.setNome(data.getNome());
        playlist.setUsuario(usuarioLogado);
        playlist.setDescricao(data.getDescricao());
        playlist.setMusicas(data.getMusicas());

        playListRepository.save(playlist);

        return ResponseEntity.ok().body("PlayList registrado com sucesso");
    }

    //Update
    public ResponseEntity<String> update(String id_playlist, PlayListDTO data){

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        PlayList playlist = playListRepository.findById(id_playlist).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum playlist encontrada com esse ID ); ."));

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playlist.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuário não é dono da playlist");
        }

        playlist.setNome(data.getNome());
        playlist.setDescricao(data.getDescricao());
        List<Musica> musicas = musicaRepository.findAllById(data.getMusicas().stream().map(Musica::getId).collect(Collectors.toList()));
        playlist.setMusicas(musicas);

        playListRepository.save(playlist);

        return ResponseEntity.ok().body("PlayList registrado com sucesso");
    }

    //Deletar

    public void delete(String id){
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        PlayList playlist = playListRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum playlist encontrada com esse ID ); ."));

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playlist.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuário não é dono da playlist");
        }

        playListRepository.delete(playlist);
    }

    //buscar por id

    public  PlayListDTO getPlayListById(String id){
        PlayList playlist = playListRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum playlist encontrada com esse ID ); ."));
        return new PlayListDTO(playlist);
    }

    //adicionar musica

    public ResponseEntity<String> addMusica(String id_playlist, String id_musica){
        PlayList playList  = playListRepository.findById(id_playlist).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum playlist encontrada com esse ID ); ."));

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playList.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Usuário não é dono da playlist");
        }

        Musica musica = musicaRepository.findById(id_musica).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma música encontrada com esse ID ); ."));
        playList.addMusica(musica);
        playListRepository.save(playList);

        return ResponseEntity.ok().body("Musica adicionada com sucesso");
    }

    //remover musica

    public ResponseEntity<String> removeMusica(String id_playlist, String id_musica){
        PlayList playList  = playListRepository.findById(id_playlist).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum playlist encontrada com esse ID ); ."));
        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        //Ve se é o mesmo id do usuário da playlist
        if (!usuarioLogado.getId().equals(playList.getUsuario().getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"Usuário não é dono da playlist");
        }

        Musica musica = musicaRepository.findById(id_musica).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhuma música encontrada com esse ID ); ."));
        playList.removeMusica(musica);
        playListRepository.save(playList);

        return ResponseEntity.ok().body("Musica removida com sucesso");
    }




}
