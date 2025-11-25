package com.projeto.api.controller;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.service.MusicaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }
    // Retorna todas as músicas com paginação
    @GetMapping
    public Page<MusicaDTO> getAllMusicas(@RequestParam Map<String,String> filtros, @PageableDefault(size = 15) Pageable pageable){
        return musicaService.getAllMusicas(filtros,pageable);
    }
    // Retorna uma música por ID
    @GetMapping("/{id}")
    public MusicaDTO getMusicaById(@PathVariable String id){
        return musicaService.getMusicaById(id);
    }

    // Cria uma nova música
    @PostMapping
    public MusicaDTO newMusica(@RequestBody MusicaDTO musicaDTO){
        return musicaService.newMusica(musicaDTO);
    }

    // Atualiza uma música existente
    @PutMapping("/{id}")
    public MusicaDTO updateMusica(@PathVariable String id, @RequestBody MusicaDTO musicaDTO) {
        return musicaService.updateMusica(id, musicaDTO);
    }

    // Deleta uma música por ID
    @DeleteMapping("/{id}")
    public void deleteMusica(@PathVariable String id) {
        musicaService.deleteMusica(id);
    }

}
