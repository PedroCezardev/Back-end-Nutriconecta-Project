package com.nutriconecta.demo.exception;

public class InvalidNotificacaoException extends RuntimeException {
    public InvalidNotificacaoException (String message) {
        super(message);
    }

    public InvalidNotificacaoException () {
        super("Notificacao procurada nao encontrado.");
    }
}