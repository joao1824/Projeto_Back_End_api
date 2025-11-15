package com.projeto.api.exception.exceptions;

public class ExternalApiException extends RuntimeException {
    public ExternalApiException(String message) {
        super(message);
    }
    public ExternalApiException(){
        super("Erro ao comunicar com a API externa.");
    }
}
