package com.projeto.api.exception.exceptions;

public class AlbumArtistNotFoundException extends RuntimeException {
    public AlbumArtistNotFoundException(String message) {
        super(message);
    }
    public AlbumArtistNotFoundException() {
        super("Álbum ou artista não encontrados");
    }
}