package com.projeto.api.controller;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlaylistAddDeleteMusicaDTO;
import com.projeto.api.service.PlayListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlayListController {


    private final PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @GetMapping()
    public List<PlayListDTO> getPlayLists(){
        return playListService.getAll();
    }

    @GetMapping("/{id}")
    public PlayListDTO getPlayList(@PathVariable String id){
        return playListService.getPlayListById(id);
    }

    @PostMapping()
    public ResponseEntity<String> addPlayList(@RequestBody PlayListDTO playListDTO){
        return playListService.novaPlaylist(playListDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayList(@PathVariable String id){
        playListService.delete(id);
        return ResponseEntity.ok("playlist deletada com sucesso");
    }

    @PutMapping("/{id_playlist}/musicas")
    public ResponseEntity<String> updatePlayList(@PathVariable String id_playlist, @RequestBody PlayListDTO data){
        return playListService.update(id_playlist,data);
    }

    @PatchMapping("/{id_playlist}/add/{id_musica}")
    public ResponseEntity<String> addMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        return playListService.addMusica(id_playlist,id_musica);
    }

    @PatchMapping("/{id_playlist}/remove/{id_musica}")
    public ResponseEntity<String> removerMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        return playListService.removeMusica(id_playlist,id_musica);
    }


}
