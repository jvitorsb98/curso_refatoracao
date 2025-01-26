package br.com.alura.adopet.api.handle;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseError (String message, HttpStatus httpStatus, LocalDateTime now) {

}