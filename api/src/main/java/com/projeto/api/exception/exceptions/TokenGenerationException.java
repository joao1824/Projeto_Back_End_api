package com.projeto.api.exception.exceptions;

public class TokenGenerationException extends RuntimeException {
    public TokenGenerationException(String message) {
        super(message);
    }
    public TokenGenerationException() {
        super("Erro na geração de token");
    }
}
