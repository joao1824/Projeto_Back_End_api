package com.projeto.api.dtos.UsuarioDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioDeletarDTO (
        String email,
        String senha
){ }
