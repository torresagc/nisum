package com.app.nisum.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NisumExeption extends RuntimeException{

    private final ErrorResponse detail;
    private final HttpStatus code;

    public NisumExeption(String messageInput, HttpStatus code){
        this.detail = ErrorResponse.builder()
                .message(messageInput)
                .build();
        this.code = code;
    }
}
