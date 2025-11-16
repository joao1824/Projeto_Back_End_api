package com.projeto.api.exception.exceptions;

public class TagExistenteException extends RuntimeException {
    public TagExistenteException(String message) {
        super(message);
    }
    public TagExistenteException() {
        super("Já existe uma tag com esse nome.");
    }
}
