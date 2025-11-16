package com.projeto.api.exception.exceptions;

public class OwnerUserException extends RuntimeException {
    public OwnerUserException(String message) {
        super(message);
    }
    public OwnerUserException() {
        super("Ação não permitida: o usuário é o dono do recurso.");
    }
}
