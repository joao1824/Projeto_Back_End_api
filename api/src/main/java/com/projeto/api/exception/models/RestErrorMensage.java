package com.projeto.api.exception.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestErrorMensage {
    private HttpStatus status;
    private String mensagem;
}
