package com.projeto.api.exception.exceptions;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException(String message) {
        super(message);
    }
    public TagNotFoundException() {
        super("Tag não encontrada.");
    }
}
