package com.projeto.api.controller;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.service.PlayListService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/playlists")
public class PlayListController {


    private final PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    // Retorna todas as playlists com paginação e ordenação
    @GetMapping()
    public Page<PlayListDTO> getAllPlayLists(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable) {
        return playListService.getAllPlaylist(filtros, pageable);
    }
    // Retorna uma playlist por ID
    @GetMapping("/{id}")
    public PlayListDTO getPlayListById(@PathVariable String id){
        return playListService.getPlayListById(id);
    }
    // Cria uma nova playlist
    @PostMapping()
    public PlayListDTO addPlayList(@RequestBody PlayListDTO playListDTO){
        return playListService.newPlayList(playListDTO);
    }
    // Deleta uma playlist por ID
    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable String id){
        playListService.deletePlayList(id);
    }
    // Atualiza uma playlist existente
    @PutMapping("/{id_playlist}/musicas")
    public PlayListDTO updatePlayList(@PathVariable String id_playlist, @RequestBody PlayListDTO data){
        return playListService.updatePlayList(id_playlist,data);
    }
    // Adiciona uma música à playlist
    @PostMapping("/{id_playlist}/musicas/{id_musica}")
    public PlayListDTO addMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        return playListService.addMusica(id_playlist,id_musica);
    }
    // Remove uma música da playlist
    @DeleteMapping("/{id_playlist}/musicas/{id_musica}")
    public void removeMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        playListService.removeMusica(id_playlist,id_musica);
    }


}
