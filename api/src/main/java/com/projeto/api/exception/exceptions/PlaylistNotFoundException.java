package com.projeto.api.exception.exceptions;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(String message) {
        super(message);
    }
    public PlaylistNotFoundException() {
        super("PlayListSpecification não encontrada.");
    }
}
