package com.example.clima.exception;

public class ClimaConsultaException extends RuntimeException {
    public ClimaConsultaException(String message) {
        super(message);
    }

    public ClimaConsultaException(String message, Throwable cause) {
        super(message, cause);
    }
}