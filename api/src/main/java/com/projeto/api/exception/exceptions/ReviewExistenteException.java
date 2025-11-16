package com.projeto.api.exception.exceptions;

public class ReviewExistenteException extends RuntimeException {
    public ReviewExistenteException(String message) {
        super(message);
    }
    public  ReviewExistenteException() {
        super("Review já existente para este usuário e álbum.");
    }
}
