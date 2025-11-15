package com.projeto.api.exception.exceptions;

public class EmptyException extends RuntimeException{
    public EmptyException(String message) {
        super(message);
    }
    public EmptyException(){
        super("Nenhum resultado encontrado.");
    }
}
