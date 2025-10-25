package com.projeto.api.controller;

import com.projeto.api.dtos.UsuarioDTO;
import com.projeto.api.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public List<UsuarioDTO> getAll(){
        return usuarioService.GetAll();
    }









    
}
