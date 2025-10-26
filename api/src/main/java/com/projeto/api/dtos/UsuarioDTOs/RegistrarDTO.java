package com.projeto.api.dtos.UsuarioDTOs;

import com.projeto.api.security.UsuarioRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record  RegistrarDTO(
        String nome,
        String email,
        String senha,
        UsuarioRole role) {
}
