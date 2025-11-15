package com.projeto.api.exception.exceptions;

public class AlbumNotFoundException extends RuntimeException {
    public AlbumNotFoundException(String message) {
        super(message);
    }
    public AlbumNotFoundException() {
        super("Album não encontrado");
    }
}
