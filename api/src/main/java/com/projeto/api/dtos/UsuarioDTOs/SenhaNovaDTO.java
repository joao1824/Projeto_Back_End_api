package com.projeto.api.dtos.UsuarioDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record SenhaNovaDTO(
        String email,
        String senha,
        String novasenha) {


}
