package com.projeto.api.exception.exceptions;

public class illegalfilterException extends RuntimeException {
    public illegalfilterException(String message) {
        super(message);
    }
    public illegalfilterException() {
        super("Um ou mais campos de filtro são inválidos.");
    }
}
