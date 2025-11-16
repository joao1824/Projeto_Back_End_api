package com.projeto.api.exception.exceptions;

public class ReapetedMusicException extends RuntimeException {
    public ReapetedMusicException(String message) {
        super(message);
    }
    public ReapetedMusicException(){
        super("A música já está na playlist");
    }

}
