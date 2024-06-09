package com.nutriconecta.demo.exception;

public class InvalidDivulgacaoException extends RuntimeException {
    public InvalidDivulgacaoException (String message) {
        super(message);
    }

    public InvalidDivulgacaoException () {
        super("Divulgacao procurada nao encontrado.");
    }
}