package com.nutriconecta.demo.exception;

public class InvalidAlimentoException extends RuntimeException {
    public InvalidAlimentoException (String message) {
        super(message);
    }

    public InvalidAlimentoException () {
        super("Alimento procurado nao encontrado.");
    }
}