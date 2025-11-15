package com.projeto.api.exception.exceptions;

public class UserNotAdminException extends RuntimeException {
    public UserNotAdminException(String message) {
        super(message);
    }
    public UserNotAdminException() {
        super("Acesso negado: usuário não é administrador");
    }
}
