package com.projeto.api.exception.exceptions;

public class UserEmailNotFoundException extends RuntimeException {
    public UserEmailNotFoundException(String message) {
        super(message);
    }
    public UserEmailNotFoundException() {
        super("Usuário não encontrado pelo email");
    }
}
