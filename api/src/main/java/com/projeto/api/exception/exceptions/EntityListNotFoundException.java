package com.projeto.api.exception.exceptions;

public class EntityListNotFoundException extends RuntimeException {
    public EntityListNotFoundException(String message) {
        super(message);
    }
    public EntityListNotFoundException() {
        super("Alguns IDs não foram encontrados");
    }
}