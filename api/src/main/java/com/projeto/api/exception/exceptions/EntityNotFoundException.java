package com.projeto.api.exception.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
    public EntityNotFoundException() {
        super("Entidade não encontrada");
    }
}
