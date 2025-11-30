package com.projeto.api.dtos.UsuarioDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public record EmailNovoDTO (
        @Column(unique = true)
        @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
        @NotBlank(message = "nome não pode estar em vazio")
        @Email
        String email,

        @Size(min = 8,max = 100, message = "senha não é valido pois possui um tamanho não planejado")
        @NotBlank(message = "senha não pode estar em vazio")
        String senha,

        @Column(unique = true)
        @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
        @NotBlank(message = "nome não pode estar em vazio")
        @Email
        String email_novo) {
    @Override
    public String email() {
        return email;
    }

    @Override
    public String email_novo() {
        return email_novo;
    }
}
