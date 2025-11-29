package com.projeto.api.controller;

import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    // get all
    @Operation(summary = "Obter todos os usuários com paginação e filtros opcionais", description = "Retorna uma lista paginada de usuários. É possível aplicar filtros opcionais através de parâmetros de consulta.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public Page<UsuarioDTO> getAllUsuario(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable){
        return usuarioService.getAllUsuarios(filtros,pageable);
    }

    //get by id
    @Operation(summary = "Obter usuário por ID", description = "Retorna os detalhes de um usuário específico com base no ID fornecido.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuário retornado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable String id){
        return ResponseEntity.ok().body(usuarioService.getUsuarioById(id));
    }


    //registrar
    @Operation(summary = "Registrar um novo usuário", description = "Cria um novo usuário com as informações fornecidas.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "Conflito - Usuário já existe")
    })
    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@RequestBody @Valid RegistrarDTO data){
        UsuarioDTO usuarioDTO = usuarioService.registerUsuario(data);
        URI uri = URI.create("/usuarios/" + usuarioDTO.getId());
        return ResponseEntity.created(uri).build();
    }

    //logar
    @Operation(summary = "Autenticar usuário", description = "Autentica um usuário com as credenciais fornecidas e retorna um token de acesso.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Credenciais inválidas"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody @Valid AuthenticationDTO data){
        return ResponseEntity.ok().body(usuarioService.loginUsuario(data));
    }


    //mudar senha
    @Operation (summary = "Alterar senha do usuário", description = "Permite que um usuário altere sua senha fornecendo a nova senha.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> changeSenha(@PathVariable String id,@RequestBody @Valid SenhaNovaDTO data){
        usuarioService.changeSenha(id,data);
        return ResponseEntity.noContent().build();
    }

    //trocar email
    @Operation(summary = "Alterar email do usuário", description = "Permite que um usuário altere seu email fornecendo o novo email.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Email alterado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/email")
    public ResponseEntity<Void> changeEmail(@PathVariable String id,@RequestBody @Valid EmailNovoDTO data){
        usuarioService.changeEmail(id,data);
        return ResponseEntity.noContent().build();
    }

    //trocar nome
    @Operation(summary = "Alterar nome do usuário", description = "Permite que um usuário altere seu nome fornecendo o novo nome.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Nome alterado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/nome")
    public ResponseEntity<Void> changeNome(@PathVariable String id,@RequestBody UsuarioNomeDTO data){
        usuarioService.changeNome(id,data);
        return ResponseEntity.noContent().build();
    }

    //deletar usuario
    @Operation(summary = "Deletar usuário", description = "Permite que um usuário exclua sua conta fornecendo as credenciais necessárias.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "Não autorizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "Proibido"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

}
