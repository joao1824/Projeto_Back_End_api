package com.projeto.api.exception.exceptions;

public class MusicaRepetidaException extends RuntimeException {
    public MusicaRepetidaException(String message) {
        super(message);
    }
    public MusicaRepetidaException(){
        super("A música já está na playlist");
    }

}
