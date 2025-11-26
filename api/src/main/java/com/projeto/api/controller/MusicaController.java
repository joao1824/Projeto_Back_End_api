package com.projeto.api.controller;

import com.projeto.api.dtos.MusicaDTOs.MusicaDTO;
import com.projeto.api.service.MusicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Retorna uma lista paginada de músicas", description = "Retorna todas as músicas com suporte a filtros e paginação.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de músicas retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public Page<MusicaDTO> getAllMusicas(@RequestParam Map<String,String> filtros, @PageableDefault(size = 15) Pageable pageable){
        return musicaService.getAllMusicas(filtros,pageable);
    }
    // Retorna uma música por ID
    @Operation(summary = "Retorna uma música por ID", description = "Busca e retorna uma música específica com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Música retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Música não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public MusicaDTO getMusicaById(@PathVariable String id){
        return musicaService.getMusicaById(id);
    }

    // Cria uma nova música
    @Operation(summary = "Cria uma nova música", description = "Adiciona uma nova música ao sistema com os dados fornecidos.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Música criada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public MusicaDTO newMusica(@RequestBody MusicaDTO musicaDTO){
        return musicaService.newMusica(musicaDTO);
    }

    // Atualiza uma música existente
    @Operation(summary = "Atualiza uma música existente", description = "Atualiza os dados de uma música específica com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Música atualizada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Música não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public MusicaDTO updateMusica(@PathVariable String id, @RequestBody MusicaDTO musicaDTO) {
        return musicaService.updateMusica(id, musicaDTO);
    }

    // Deleta uma música por ID
    @Operation(summary = "Deleta uma música por ID", description = "Remove uma música específica do sistema com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Música deletada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Música não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public void deleteMusica(@PathVariable String id) {
        musicaService.deleteMusica(id);
    }

}
