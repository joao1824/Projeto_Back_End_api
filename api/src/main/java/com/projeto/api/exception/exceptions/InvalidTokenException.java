package com.projeto.api.exception.exceptions;


public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
    public InvalidTokenException() {
        super("Token inválido");
    }
}