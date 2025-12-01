package com.projeto.api.controller;

import com.projeto.api.dtos.AlbumDTOs.AlbumDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioDTO;
import com.projeto.api.mapper.dtos.AlbumMapper;
import com.projeto.api.models.Album;
import com.projeto.api.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.Map;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    public AlbumController(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    @Operation(summary = "Busca um álbum no Spotify ou cria um novo álbum se não existir", description = "Busca um álbum no Spotify usando o nome do álbum e do artista fornecidos. Se o álbum não for encontrado, cria um novo álbum no sistema.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Álbum encontrado ou criado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/spotify")
    public ResponseEntity<AlbumDTO> buscarOuCriarAlbum(
            @RequestParam String nomeAlbum,
            @RequestParam String nomeArtista
    ) {
        Album album = albumService.getOrCreateAlbum(nomeAlbum, nomeArtista);
        return ResponseEntity.ok(albumMapper.toAlbumDTO(album));
    }

    // Retorna todos os albums com paginação
    @Operation(summary = "Retorna todos os álbuns com paginação e filtros opcionais", description = "Retorna uma lista paginada de todos os álbuns, permitindo filtros opcionais através de parâmetros de consulta.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de álbuns retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido")
    })
    @GetMapping
    public Page<AlbumDTO> getAllAlbums(@PageableDefault(size = 15) Pageable pageable,@RequestParam Map<String, String> filtros) {
        return albumService.getAllAlbums(pageable, filtros);
    }

    // Retorna um album por ID
    @Operation(summary = "Retorna um álbum por ID",description = "Retorna um álbum específico com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Álbum retornado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Álbum não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable String id){
        return ResponseEntity.ok().body(albumService.getAlbumById(id));
    }

    // Cria um novo album
    @Operation(summary = "Cria um novo álbum", description = "Cria um novo álbum com os dados fornecidos no corpo da requisição")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Álbum criado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido")
    })
    @PostMapping
    public ResponseEntity<AlbumDTO> newAlbum(@Valid @RequestBody AlbumDTO dados){
        AlbumDTO albumDTO = albumService.newAlbum(dados);
        URI uri = URI.create("/albums/" + albumDTO.getId());
        return ResponseEntity.created(uri).body(albumDTO);
    }

    // Atualiza um album existente
    @Operation(summary = "Atualiza um álbum existente por ID", description = "Atualiza um álbum existente com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Álbum atualizado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Álbum não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido")
    })
    @PutMapping("/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable String id,@Valid @RequestBody AlbumDTO albumDTO) {
        return ResponseEntity.ok(albumService.updateAlbum(id, albumDTO));
    }

    // Deleta um album por ID
    @Operation(summary = "Deleta um álbum por ID", description = "Deleta um álbum específico com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Álbum deletado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Álbum não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

}
