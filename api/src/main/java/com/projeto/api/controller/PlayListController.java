package com.projeto.api.controller;

import com.projeto.api.dtos.PlayListDTO;
import com.projeto.api.service.PlayListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlist")
public class PlayListController {

    private PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    @GetMapping()
    public List<PlayListDTO> GetPlayList(){
        return playListService.GetAll();
    }

    @PostMapping("/nova-playlist")
    public PlayListDTO AddPlayList(@RequestBody PlayListDTO playListDTO){
        return playListService.CreateNew(playListDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> DeletePlayList(@PathVariable("id") String id){
        playListService.Delete(id);
        return ResponseEntity.ok("playlist deletada com sucesso");
    }

    @PutMapping("/update/{id}")
    public PlayListDTO UpdatePlayList(@RequestBody PlayListDTO playListDTO, @PathVariable("id") String id){
        return playListService.Update(playListDTO,id);
    }

}
