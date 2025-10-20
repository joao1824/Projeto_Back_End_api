package com.projeto.api.service;

import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



}
