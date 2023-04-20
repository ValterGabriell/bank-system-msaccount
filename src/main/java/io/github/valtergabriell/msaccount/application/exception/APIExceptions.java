package io.github.valtergabriell.msaccount.application.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record APIExceptions (String message, HttpStatus statusCode, ZonedDateTime timestamp){}
