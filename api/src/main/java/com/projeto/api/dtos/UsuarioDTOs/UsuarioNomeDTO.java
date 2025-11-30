package com.projeto.api.dtos.UsuarioDTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioNomeDTO(
        @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
        @NotBlank(message = "nome não pode estar em vazio")
        String nome
) {
}
