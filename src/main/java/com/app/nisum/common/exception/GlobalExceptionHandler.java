package com.app.nisum.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(
            final Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.NOT_CHECK).build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NisumExeption.class)
    public ResponseEntity<ErrorResponse> nisumExceptionHandler(
            final NisumExeption exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getDetail(), exception.getCode());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> badCredentialsExceptionHandler(
            final BadCredentialsException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.BAD_CREDENTIAL).build(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<ErrorResponse> accountStatusExceptionHandler(
            final AccountStatusException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.ACCOUNT_STATUS).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDeniedExceptionHandler(
            final AccessDeniedException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.ACCESS_DENIED).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> signatureExceptionHandler(
            final SignatureException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.SIGNATURE).build(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> expiredJwtExceptionHandler(
            final ExpiredJwtException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .message(ErrorMessage.EXPIRED_JWT).build(), HttpStatus.UNAUTHORIZED);
    }

}
