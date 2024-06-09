package com.nutriconecta.demo.exception;

public class InvalidComentarioException extends RuntimeException {
    public InvalidComentarioException (String message) {
        super(message);
    }

    public InvalidComentarioException () {
        super("Comentario procurado nao encontrado.");
    }
}