package com.projeto.api.dtos.UsuarioDTOs;

import com.projeto.api.security.UsuarioRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;


public record  RegistrarDTO(
        @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
        @NotBlank(message = "nome não pode estar em vazio")
        String nome,

        @Column(unique = true)
        @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
        @NotBlank(message = "nome não pode estar em vazio")
        @Email
        String email,

        @Size(min = 8,max = 100, message = "senha não é valido pois possui um tamanho não planejado")
        @NotBlank(message = "senha não pode estar em vazio")
        String senha,

        @NotNull(message = "Role não pode ser nulo")
        UsuarioRole role) {
}
