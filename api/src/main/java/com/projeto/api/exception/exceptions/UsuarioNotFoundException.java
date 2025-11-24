package com.projeto.api.exception.exceptions;

public class UsuarioNotFoundException extends RuntimeException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }
    public UsuarioNotFoundException() {
        super("Usuário não encontrado");
    }
}
