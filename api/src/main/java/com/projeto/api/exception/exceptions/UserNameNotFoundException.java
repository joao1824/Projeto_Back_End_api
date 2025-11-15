package com.projeto.api.exception.exceptions;

public class UserNameNotFoundException extends RuntimeException {
    public UserNameNotFoundException(String message) {
        super(message);
    }
    public UserNameNotFoundException() {
        super("Usuário não encontrado pelo nome");
    }
}