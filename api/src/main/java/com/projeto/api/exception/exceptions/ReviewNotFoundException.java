package com.projeto.api.exception.exceptions;

public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(){
        super("Review não encontrada");
    }
}

