package com.projeto.api.controller;

import com.projeto.api.service.UsuarioService;

public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    
}
