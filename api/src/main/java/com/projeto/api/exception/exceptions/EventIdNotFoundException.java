package com.projeto.api.exception.exceptions;

public class EventIdNotFoundException extends RuntimeException {
    public EventIdNotFoundException(String message) {
        super(message);
    }
    public EventIdNotFoundException() {
        super("ID não encontrado");
    }
}
