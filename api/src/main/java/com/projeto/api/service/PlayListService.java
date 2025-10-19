package com.projeto.api.service;

import com.projeto.api.dtos.PlayListDTO;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import com.projeto.api.repository.MusicaRepository;
import com.projeto.api.repository.PlayListRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayListService {

    private PlayListRepository playListRepository;
    private MusicaRepository musicaRepository;

    public PlayListService(PlayListRepository playListRepository, MusicaRepository musicaRepository) {
        this.playListRepository = playListRepository;
        this.musicaRepository = musicaRepository;
    }

    //Retorna todos
    public List<PlayListDTO> GetAll() {
        List<PlayList> playlist = playListRepository.findAll();

        if (playlist.isEmpty()) {
            throw new EntityNotFoundException("Nenhum playlist encontrada ); .");
        }
        return playlist.stream().map(PlayListDTO::new).collect(Collectors.toList());
    }

    //Adiciona um novo
    public PlayListDTO CreateNew(PlayListDTO playListDTO){
        PlayList playlist = new PlayList();

        //Esperar a estar pronto musica e usuario PARA FUNCIONAR

//        if (playListDTO.getNome() == null || playListDTO.getNome().isBlank() ||
//                playListDTO.getUsuario() == null ||
//                    playListDTO.getMusicas() == null ||
//                        playListDTO.getDescricao() == null || playListDTO.getDescricao().isBlank()
//        ){
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo não pode ser nulo ou vazio ); .");
//        }


        playlist.setNome(playListDTO.getNome());
        playlist.setUsuario(playListDTO.getUsuario());
        playlist.setDescricao(playListDTO.getDescricao());
        playlist.setMusicas(playListDTO.getMusicas());

        return new PlayListDTO(playListRepository.save(playlist));
    }

    //Update
    public PlayListDTO Update(PlayListDTO playListDTO,String id){
        PlayList playlist = playListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum playlist encontrada com esse ID ); ."));

        //Esperar a estar pronto musica e usuario PARA FUNCIONAR


//        if (playListDTO.getNome() == null || playListDTO.getNome().isBlank() ||
//                playListDTO.getDescricao() == null || playListDTO.getDescricao().isBlank() ||
//                playListDTO.getUsuario() == null ||
//                playListDTO.getMusicas() == null) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo não pode ser nulo ou vazio ); .");
//        }

        playlist.setId(id);
        playlist.setNome(playListDTO.getNome());
        playlist.setDescricao(playListDTO.getDescricao());
        playlist.setMusicas(playListDTO.getMusicas());

        return new PlayListDTO(playListRepository.save(playlist));
    }

    //Deletar

    public void Delete(String id){
        PlayList playList = playListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum playlist encontrada com esse ID ); ."));
        playListRepository.delete(playList);
    }

    //buscar por id

    public  PlayListDTO GetPlayListById(String id){
        PlayList playlist = playListRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nenhum playlist encontrada com esse ID ); ."));
        return new PlayListDTO(playlist);
    }

    //adicionar musica

    public PlayListDTO AddMusica(String id_musica, String id_playlist){
        PlayList playList  = playListRepository.findById(id_playlist).orElseThrow(() -> new EntityNotFoundException("Nenhum playlist encontrada com esse ID ); ."));
        Musica musica = musicaRepository.findById(id_musica).orElseThrow(() -> new EntityNotFoundException("Nenhuma música encontrada com esse ID ); ."));
        playList.addMusica(musica);
        return new PlayListDTO(playList);
    }

    //remover musica

    public void RemoveMusica(String id_musica, String id_playlist){
        PlayList playList  = playListRepository.findById(id_playlist).orElseThrow(() -> new EntityNotFoundException("Nenhum playlist encontrada com esse ID ); ."));
        Musica musica = musicaRepository.findById(id_musica).orElseThrow(() -> new EntityNotFoundException("Nenhuma música encontrada com esse ID ); ."));
        playList.removeMusica(musica);
    }




}
