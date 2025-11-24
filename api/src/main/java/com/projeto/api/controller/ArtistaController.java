package com.projeto.api.controller;


import com.projeto.api.dtos.ArtistasDTOs.ArtistaDTO;
import com.projeto.api.mapper.dtos.ArtistaMapper;
import com.projeto.api.models.Artista;
import com.projeto.api.service.ArtistaService;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaMapper artistaMapper;
    private ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService, ArtistaMapper artistaMapper) {
        this.artistaService = artistaService;
        this.artistaMapper = artistaMapper;
    }

    @GetMapping("/spotify")
    public ResponseEntity<ArtistaDTO> buscarOuCriarArtista(@RequestParam String nome) {
        Artista artista = artistaService.getOrCreateArtista(nome);
        return ResponseEntity.ok(artistaMapper.toDto(artista));
    }

    // Retorna todos os artistas com paginação
    @GetMapping
    public Page<ArtistaDTO> getAllArtista(@RequestParam Map<String, String> filtros,
                                          @PageableDefault (size = 15) Pageable pageable) {
        return artistaService.getAllArtistas(filtros,pageable);
    }

    // Retorna um artista por ID
    @GetMapping("/{id}")
    public ArtistaDTO getArtistaById(@PathVariable String id) {
        return artistaService.getArtistaById(id);
    }

    // Adiciona um novo artista
    @PostMapping
    public ArtistaDTO newArtista(@RequestBody ArtistaDTO artistaDTO) {
        return artistaService.newArtista(artistaDTO);
    }

    // Atualiza um artista existente
    @PutMapping("/{id}")
    public ArtistaDTO updateArtista(@PathVariable String id, @RequestBody ArtistaDTO artistaDTO) {
        return artistaService.updateArtista(id, artistaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteArtista(@PathVariable String id) {
        artistaService.deleteArtista(id);
    }

}
