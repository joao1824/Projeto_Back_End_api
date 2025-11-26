package com.projeto.api.controller;

import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.service.PlayListService;
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
@RequestMapping("/playlists")
public class PlayListController {


    private final PlayListService playListService;


    public PlayListController(PlayListService playListService) {
        this.playListService = playListService;
    }

    // Retorna todas as playlists com paginação e ordenação
    @Operation(summary = "Retorna todas as playlists com paginação e ordenação", description = "Retorna uma lista paginada de todas as playlists disponíveis, permitindo ordenação por campos específicos.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Playlists retornadas com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping()
    public Page<PlayListDTO> getAllPlayLists(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable) {
        return playListService.getAllPlaylist(filtros, pageable);
    }
    // Retorna uma playlist por ID
    @Operation(summary = "Retorna uma playlist por ID", description = "Retorna os detalhes de uma playlist específica com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Playlist retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}")
    public PlayListDTO getPlayListById(@PathVariable String id){
        return playListService.getPlayListById(id);
    }
    // Cria uma nova playlist

    @Operation(summary = "Cria uma nova playlist",description = "Cria uma nova playlist com os dados fornecidos no corpo da requisição.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Playlist criada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping()
    public PlayListDTO addPlayList(@RequestBody PlayListDTO playListDTO){
        return playListService.newPlayList(playListDTO);
    }
    // Deleta uma playlist por ID

    @Operation (summary = "Deleta uma playlist por ID",description = "Deleta uma playlist existente especificada pelo ID da playlist.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Playlist deletada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public void deletePlayList(@PathVariable String id){
        playListService.deletePlayList(id);
    }



    // Atualiza uma playlist existente
    @Operation(summary = "Atualiza uma playlist existente", description = "Atualiza os detalhes de uma playlist existente especificada pelo ID da playlist.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Playlist atualizada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Playlist não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping("/{id_playlist}/musicas")
    public PlayListDTO updatePlayList(@PathVariable String id_playlist, @RequestBody PlayListDTO data){
        return playListService.updatePlayList(id_playlist,data);
    }

    // Adiciona uma música à playlist

    @Operation(summary = "Adiciona uma música à playlist",description = "Adiciona uma música existente à playlist especificada pelo ID da playlist.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Música adicionada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Playlist ou música não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/{id_playlist}/musicas/{id_musica}")
    public PlayListDTO addMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        return playListService.addMusica(id_playlist,id_musica);
    }


    // Remove uma música da playlist
    @Operation(summary = "Remove uma música da playlist",description = "Remove uma música específica de uma playlist existente")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Música removida com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Playlist ou música não encontrada"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id_playlist}/musicas/{id_musica}")
    public void removeMusica(@PathVariable String id_playlist, @PathVariable String id_musica){
        playListService.removeMusica(id_playlist,id_musica);
    }


}
