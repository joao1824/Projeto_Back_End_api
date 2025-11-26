package com.projeto.api.controller;


import com.projeto.api.dtos.ArtistasDTOs.ArtistaDTO;
import com.projeto.api.mapper.dtos.ArtistaMapper;
import com.projeto.api.models.Artista;
import com.projeto.api.service.ArtistaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaMapper artistaMapper;
    private ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService, ArtistaMapper artistaMapper) {
        this.artistaService = artistaService;
        this.artistaMapper = artistaMapper;
    }



    @Operation(summary = "Busca um artista no Spotify pelo nome. Se não existir, cria um novo artista no sistema.", description = "Este endpoint busca um artista no Spotify pelo nome fornecido. Se o artista não for encontrado, um novo artista é criado no sistema com base nas informações obtidas do Spotify.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Artista encontrado ou criado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/spotify")
    public ResponseEntity<ArtistaDTO> buscarOuCriarArtista(@RequestParam String nome) {
        Artista artista = artistaService.getOrCreateArtista(nome);
        return ResponseEntity.ok(artistaMapper.toDto(artista));
    }

    // Retorna todos os artistas com paginação
    @Operation(summary = "Retorna uma lista paginada de artistas com base nos filtros fornecidos.", description = "Este endpoint retorna uma lista paginada de artistas. Os usuários podem aplicar filtros através dos parâmetros de consulta para refinar os resultados.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de artistas retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping
    public Page<ArtistaDTO> getAllArtista(@RequestParam Map<String, String> filtros,
                                          @PageableDefault (size = 15) Pageable pageable) {
        return artistaService.getAllArtistas(filtros,pageable);
    }

    // Retorna um artista por ID
    @Operation(summary = "Retorna os detalhes de um artista específico com base no ID fornecido.", description = "Este endpoint retorna os detalhes de um artista específico identificado pelo ID fornecido na URL.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Artista retornado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Artista não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public ArtistaDTO getArtistaById(@PathVariable String id) {
        return artistaService.getArtistaById(id);
    }

    // Adiciona um novo artista
    @Operation(summary = "Adiciona um novo artista ao sistema.", description = "Este endpoint permite a criação de um novo artista no sistema com base nos dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Artista criado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    public ArtistaDTO newArtista(@RequestBody ArtistaDTO artistaDTO) {
        return artistaService.newArtista(artistaDTO);
    }

    // Atualiza um artista existente
    @Operation(summary = "Atualiza os detalhes de um artista existente.",
            description = "Este endpoint permite a atualização dos detalhes de um artista existente identificado pelo ID fornecido na URL.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Artista atualizado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Artista não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id}")
    public ArtistaDTO updateArtista(@PathVariable String id, @RequestBody ArtistaDTO artistaDTO) {
        return artistaService.updateArtista(id, artistaDTO);
    }

    @Operation(summary = "Exclui um artista do sistema.", description = "Este endpoint permite a exclusão de um artista existente identificado pelo ID fornecido na URL.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Artista excluído com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Artista não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public void deleteArtista(@PathVariable String id) {
        artistaService.deleteArtista(id);
    }

}
