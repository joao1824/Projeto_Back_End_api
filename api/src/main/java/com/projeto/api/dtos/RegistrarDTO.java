package com.projeto.api.dtos;

import com.projeto.api.security.UsuarioRole;

public record  RegistrarDTO(String nome, String email, String senha, UsuarioRole role) {
}
