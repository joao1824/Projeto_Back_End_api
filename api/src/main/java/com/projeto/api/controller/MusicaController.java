package com.projeto.api.controller;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.service.MusicaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }
    // Retorna todas as músicas com paginação
    @GetMapping
    public Page<MusicaDTO> getMusicas(Pageable pageable){
        return musicaService.getAllMusicas(pageable);
    }
    // Retorna uma música por ID
    @GetMapping("/{id}")
    public MusicaDTO getMusicaById(@PathVariable String id){
        return musicaService.getMusicaById(id);
    }

    // Cria uma nova música
    @PostMapping
    public MusicaDTO novaMusica(@RequestBody MusicaDTO musicaDTO){
        return musicaService.novaMusica(musicaDTO);
    }

    // Atualiza uma música existente
    @PutMapping("/{id}")
    public MusicaDTO atualizarMusica(@PathVariable String id, @RequestBody MusicaDTO musicaDTO) {
        return musicaService.atualizarMusica(id, musicaDTO);
    }

    // Deleta uma música por ID
    @DeleteMapping("/{id}")
    public void deletarMusica(@PathVariable String id) {
        musicaService.deletarMusica(id);
    }

}
