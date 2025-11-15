package com.projeto.api.exception.exceptions;

public class ArtistaNotFoundException extends RuntimeException {
    public ArtistaNotFoundException(String message) {
        super(message);
    }
    public ArtistaNotFoundException() {
        super("Artista não encontrado");
    }
}
