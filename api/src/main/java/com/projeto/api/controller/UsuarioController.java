package com.projeto.api.controller;

import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    // get all
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public Page<UsuarioDTO> getAllUsuario(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable){
        return usuarioService.getAllUsuarios(filtros,pageable);
    }

    //get by id
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable String id){
        return usuarioService.getUsuarioById(id);
    }


    //registrar
    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid RegistrarDTO data){
        return usuarioService.registerUsuario(data);
    }

    //logar
    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return usuarioService.loginUsuario(data);
    }


    //mudar senha
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/senha")
    public ResponseEntity<String> changeSenha(@PathVariable String id,@RequestBody @Valid SenhaNovaDTO data){
        return usuarioService.changeSenha(id,data);
    }

    //trocar email
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/email")
    public ResponseEntity<String> changeEmail(@PathVariable String id,@RequestBody @Valid EmailNovoDTO data){
        return usuarioService.changeEmail(id,data);
    }

    //trocar nome
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/nome")
    public ResponseEntity<String> changeNome(@PathVariable String id,@RequestBody UsuarioDTO data){
       return usuarioService.changeNome(id,data);
    }

    //deletar usuario
    @SecurityRequirement(name = "bearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable String id,@RequestBody UsuarioDTO data){
        return usuarioService.deleteUsuario(id,data);
    }

}
