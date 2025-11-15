package com.projeto.api.exception.exceptions;

public class MusicaNotFoundException extends RuntimeException {
    public MusicaNotFoundException(String message) {
        super(message);
    }
    public MusicaNotFoundException() {
        super("Música não encontrada.");
    }
}
