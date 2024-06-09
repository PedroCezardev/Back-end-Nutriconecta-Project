package com.nutriconecta.demo.exception;

public class InvalidReceptorException extends RuntimeException {
    public InvalidReceptorException (String message) {
        super(message);
    }

    public InvalidReceptorException () {
        super("Receptor procurado nao encontrado.");
    }
}