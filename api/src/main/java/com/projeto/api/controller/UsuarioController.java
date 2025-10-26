package com.projeto.api.controller;

import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/conta")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    // get all

    @GetMapping("/usuarios")
    public List<UsuarioDTO> getAll(){
        return usuarioService.GetAll();
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

    @PostMapping("/usuario/trocar-senha")
    public ResponseEntity<String> mudarSenha(@RequestBody @Valid SenhaNovaDTO data){
        return usuarioService.MudarSenha(data);
    }

    //trocar email

    @PostMapping("/usuario/troca-email")
    public ResponseEntity<String> mudarSenha(@RequestBody @Valid EmailNovoDTO data){
        return usuarioService.MudarEmail(data);
    }

    //trocar nome

    @PostMapping("/usuario/trocar-nome")
    public ResponseEntity<String> mudarNome(@RequestBody UsuarioDTO data){
       return usuarioService.MudarNome(data);
    }

    //deletar usuario
    @PostMapping("/usuario/deletar-usuario")
    public ResponseEntity<String> deletarUsuario(@RequestBody UsuarioDTO data){
        return usuarioService.DeletarUsuario(data);
    }

}
