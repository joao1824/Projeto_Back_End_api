package com.projeto.api.controller;

import com.projeto.api.dtos.PlayListDTO;
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
    public List<PlayListDTO> getPlayList(){
        return playListService.GetAll();
    }

    @PostMapping("/nova-playlist")
    public PlayListDTO addPlayList(@RequestBody PlayListDTO playListDTO){
        return playListService.CreateNew(playListDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletePlayList(@PathVariable("id") String id){
        playListService.Delete(id);
        return ResponseEntity.ok("playlist deletada com sucesso");
    }

    @PutMapping("/update/{id}")
    public PlayListDTO updatePlayList(@RequestBody PlayListDTO playListDTO, @PathVariable("id") String id){
        return playListService.Update(playListDTO,id);
    }

    @PatchMapping("/adicionar-musica/{id_playlist}/{id_musica}")
    public PlayListDTO addMusica(@PathVariable("id_musica") String id_musica,@PathVariable("id_playlist") String id_playlist){
        return playListService.AddMusica(id_musica,id_playlist);
    }

    @PatchMapping("/remover-musica/{id_playlist}/{id_musica}")
    public ResponseEntity<String> removerMusica(@PathVariable("id_playlist") String id_playlist, @PathVariable("id_musica") String id_musica){
        playListService.RemoveMusica(id_playlist, id_musica);
        return ResponseEntity.ok("música removida com sucesso");
    }




}
