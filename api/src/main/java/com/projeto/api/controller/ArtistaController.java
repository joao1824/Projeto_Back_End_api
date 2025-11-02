package com.projeto.api.controller;


import com.projeto.api.dtos.ArtistaDTO;
import com.projeto.api.models.Artista;
import com.projeto.api.service.ArtistaService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    private ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    // Retorna todos os artistas com paginação
    @GetMapping
    public Page<ArtistaDTO> getAllArtista(@PageableDefault Pageable pageable) {
        return artistaService.getAllArtistas(pageable);
    }

    // Retorna um artista por ID
    @GetMapping("/{id}")
    public ArtistaDTO getArtistaById(@PathVariable String id) {
        return artistaService.getArtistaById(id);
    }

    // Adiciona um novo artista
    @PostMapping
    public ArtistaDTO novoArtista(@RequestBody ArtistaDTO artistaDTO) {
        return artistaService.novoArtista(artistaDTO);
    }

    // Atualiza um artista existente
    @PutMapping("/{id}")
    public ArtistaDTO atualizarArtista(@PathVariable String id, @RequestBody ArtistaDTO artistaDTO) {
        return artistaService.atualizarArtista(id, artistaDTO);
    }

    @DeleteMapping("/{id}")
    public void deletarArtista(@PathVariable String id) {
        artistaService.deletarArtista(id);
    }

}
