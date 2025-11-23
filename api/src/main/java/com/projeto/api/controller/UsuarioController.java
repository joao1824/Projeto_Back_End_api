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
@RequestMapping("/conta")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    // get all

    @GetMapping("/usuarios")
    public Page<UsuarioDTO> getAll(@RequestParam Map<String,String> filtros, @PageableDefault(size = 10) Pageable pageable){
        return usuarioService.GetAllUsuarios(filtros,pageable);
    }


    //logar

    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        return usuarioService.LoginUsuario(data);
    }

    //registrar

    @PostMapping("/registrar")
    public ResponseEntity<String> register(@RequestBody @Valid RegistrarDTO data){
        return usuarioService.RegistrarUsuario(data);
    }

    //mudar senha

    @PatchMapping("/usuario/senha")
    public ResponseEntity<String> mudarSenha(@RequestBody @Valid SenhaNovaDTO data){
        return usuarioService.MudarSenha(data);
    }

    //trocar email

    @PatchMapping("/usuario/email")
    public ResponseEntity<String> mudarEmail(@RequestBody @Valid EmailNovoDTO data){
        return usuarioService.MudarEmail(data);
    }

    //trocar nome

    @PatchMapping("/usuario/nome")
    public ResponseEntity<String> mudarNome(@RequestBody UsuarioDTO data){
       return usuarioService.MudarNome(data);
    }

    //deletar usuario
    @DeleteMapping("/usuario")
    public ResponseEntity<String> deletarUsuario(@RequestBody UsuarioDTO data){
        return usuarioService.DeletarUsuario(data);
    }

}
