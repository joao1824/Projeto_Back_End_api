package com.projeto.api.controller;

import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.service.UsuarioService;
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

    public Page<UsuarioDTO> getAllUsuario(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable){
        return usuarioService.getAllUsuarios(filtros,pageable);
    }

    //get by id
    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable String id){
        return usuarioService.getUsuarioById(id);
    }


    //registrar

    @PostMapping
    public ResponseEntity<String> register(@RequestBody @Valid RegistrarDTO data){
        return usuarioService.registrarUsuario(data);
    }

    //logar

    @PostMapping("/auth/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return usuarioService.loginUsuario(data);
    }


    //mudar senha

    @PatchMapping("/{id}/senha")
    public ResponseEntity<String> mudarSenha(@RequestParam String id,@RequestBody @Valid SenhaNovaDTO data){
        return usuarioService.mudarSenha(id,data);
    }

    //trocar email

    @PatchMapping("/{id}/email")
    public ResponseEntity<String> mudarEmail(@RequestParam String id,@RequestBody @Valid EmailNovoDTO data){
        return usuarioService.mudarEmail(id,data);
    }

    //trocar nome

    @PatchMapping("/{id}/nome")
    public ResponseEntity<String> mudarNome(@RequestParam String id,@RequestBody UsuarioDTO data){
       return usuarioService.mudarNome(id,data);
    }

    //deletar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarUsuario(@RequestParam String id,@RequestBody UsuarioDTO data){
        return usuarioService.deletarUsuario(id,data);
    }

}
