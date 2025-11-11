package com.projeto.api.controller;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.models.Album;
import com.projeto.api.repository.AlbumRepository;
import com.projeto.api.service.AlbumService;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/spotify")
    public ResponseEntity<AlbumDTO> buscarOuCriarAlbum(
            @RequestParam String nomeAlbum,
            @RequestParam String nomeArtista
    ) {
        Album album = albumService.getOrCreateAlbum(nomeAlbum, nomeArtista);
        return ResponseEntity.ok(new AlbumDTO(album));
    }



    // Retorna todos os albums com paginação
    @GetMapping
    public Page<AlbumDTO> getAllAlbums(@PageableDefault Pageable pageable) {
        return albumService.getAllAlbums(pageable);
    }

    // Retorna um album por ID
    @GetMapping("/{id}")
    public AlbumDTO getAlbumById(@PathVariable String id){
        return albumService.getAlbumById(id);
    }

    // Cria um novo album

    @PostMapping
    public AlbumDTO novoAlbum(@RequestBody AlbumDTO albumDTO){
        return albumService.novoAlbum(albumDTO);
    }

    // Atualiza um album existente
    @PutMapping("/{id}")
    public AlbumDTO atualizarAlbum(@PathVariable String id, @RequestBody AlbumDTO albumDTO) {
        return albumService.atualizarAlbum(id, albumDTO);
    }

    // Deleta um album por ID
    @DeleteMapping("/{id}")
    public void deletarAlbum(@PathVariable String id) {
        albumService.deletarAlbum(id);
    }

}
