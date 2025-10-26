package com.projeto.api.controller;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.service.PlayListService;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/api/playlist")
public class PlayListController {


    private final PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    // Retorna todas as playlists com paginação e ordenação
    @GetMapping()
    public Page<PlayListDTO> getPlayLists(@PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return playListService.getAll(pageable);
    }
    // Retorna uma playlist por ID
    @GetMapping("/{id}")
    public PlayListDTO getPlayListById(@PathVariable String id){
        return playListService.getPlayListById(id);
    }
    // Cria uma nova playlist
    @PostMapping()
    public PlayListDTO addPlayList(@RequestBody PlayListDTO playListDTO){
        return playListService.novaPlaylist(playListDTO);
    }
    // Deleta uma playlist por ID
    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable String id){
        playListService.delete(id);
    }
    // Atualiza uma playlist existente
    @PutMapping("/{id_playlist}/musicas")
    public PlayListDTO updatePlayList(@PathVariable String id_playlist, @RequestBody PlayListDTO data){
        return playListService.update(id_playlist,data);
    }
    // Adiciona uma música à playlist
    @PatchMapping("/{id_playlist}/add/{id_musica}")
    public PlayListDTO addMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        return playListService.addMusica(id_playlist,id_musica);
    }
    // Remove uma música da playlist
    @PatchMapping("/{id_playlist}/remove/{id_musica}")
    public void removerMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        playListService.removeMusica(id_playlist,id_musica);
    }


}
