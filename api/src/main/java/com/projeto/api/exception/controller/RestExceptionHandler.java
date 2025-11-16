package com.projeto.api.exception.controller;

import com.projeto.api.exception.exceptions.*;
import com.projeto.api.exception.models.RestErrorMensage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CredentialsInvalidException.class)
    private ResponseEntity<RestErrorMensage> handleInvalidCredentialsException(CredentialsInvalidException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restErrorMensage);
    };


    @ExceptionHandler(EmailAlreadyExistsException.class)
    private ResponseEntity<RestErrorMensage> handleEmailAlreadyExistsException(EmailAlreadyExistsException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMensage);
    };


    @ExceptionHandler(TokenGenerationException.class)
    private ResponseEntity<RestErrorMensage> handleTokenGeracaoException(TokenGenerationException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restErrorMensage);
    };


    @ExceptionHandler(TagExistenteException.class)
    private ResponseEntity<RestErrorMensage> handleTagExistenteException(TagExistenteException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMensage);
    };


    @ExceptionHandler(OwnerUserException.class)
    private ResponseEntity<RestErrorMensage> handleUsuarioDonoException(OwnerUserException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(restErrorMensage);
    };

    @ExceptionHandler(TagNotFoundException.class)
    private ResponseEntity<RestErrorMensage> handleTagNotFoundException(TagNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };

    @ExceptionHandler(ReviewExistenteException.class)
    private ResponseEntity<RestErrorMensage> handleReviewExistenteException(ReviewExistenteException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMensage);
    };

    @ExceptionHandler(ReapetedMusicException.class)
    private ResponseEntity<RestErrorMensage> handleMusicaRepetidaException(ReapetedMusicException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.CONFLICT, exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMensage);
    };


    @ExceptionHandler(PlaylistNotFoundException.class)
    private ResponseEntity<RestErrorMensage> handlePlaylistNotFoundException(PlaylistNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };

    @ExceptionHandler(MusicaNotFoundException.class)
    private ResponseEntity<RestErrorMensage> handleMusicaNotFoundException(MusicaNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };

    @ExceptionHandler(ArtistaNotFoundException.class)
    private ResponseEntity<RestErrorMensage> handleArtistaNotFoundException(ArtistaNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };

    @ExceptionHandler(AlbumNotFoundException.class)
    private ResponseEntity<RestErrorMensage> handleAlbumNotFoundException(AlbumNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };


    @ExceptionHandler(ExternalApiException.class)
    private ResponseEntity<RestErrorMensage> handleExternalApiException(ExternalApiException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.SERVICE_UNAVAILABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(restErrorMensage);
    };


    @ExceptionHandler(EmptyException.class)
    private ResponseEntity<RestErrorMensage> handleEmptyException(EmptyException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };

    @ExceptionHandler(EventIdNotFoundException.class)
    private ResponseEntity<RestErrorMensage> eventIdNotFound(EventIdNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND,exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<RestErrorMensage> invalidToken(InvalidTokenException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.UNAUTHORIZED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(restErrorMensage);
    };

    @ExceptionHandler(UserEmailNotFoundException.class)
    private ResponseEntity<RestErrorMensage> userEmailNotFound(UserEmailNotFoundException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMensage);
    };


    @ExceptionHandler(UserNotAdminException.class)
    private ResponseEntity<RestErrorMensage> userNotAdmin(UserNotAdminException exception) {
        RestErrorMensage restErrorMensage = new RestErrorMensage(HttpStatus.FORBIDDEN, exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(restErrorMensage);
    };



}
