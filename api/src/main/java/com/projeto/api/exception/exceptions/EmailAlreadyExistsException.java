package com.projeto.api.exception.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);

    }

    public EmailAlreadyExistsException() {
        super("O email fornecido já está em uso.");
    }
}
