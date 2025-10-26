package com.projeto.api.dtos.UsuarioDTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

public record EmailNovoDTO (
                            String email,
                            String senha,
                            String email_novo) { }
