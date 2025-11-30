package com.projeto.api.dtos.UsuarioDTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record SenhaNovaDTO(
        @Column(unique = true)
        @Size(min = 0, max = 100,message = "nome possui um tamanho não planejado")
        @NotBlank(message = "nome não pode estar em vazio")
        @Email
        String email,

        @Size(min = 8,max = 100, message = "senha não é valido pois possui um tamanho não planejado")
        @NotBlank(message = "senha não pode estar em vazio")
        String senha,


        @Size(min = 8,max = 100, message = "senha não é valido pois possui um tamanho não planejado")
        @NotBlank(message = "senha não pode estar em vazio")
        String novasenha) {


}
