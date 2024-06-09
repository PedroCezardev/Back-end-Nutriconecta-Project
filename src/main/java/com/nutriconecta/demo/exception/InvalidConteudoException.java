package com.nutriconecta.demo.exception;

public class InvalidConteudoException extends RuntimeException {
    public InvalidConteudoException (String message) {
        super(message);
    }

    public InvalidConteudoException () {
        super("Conteudo procurado nao encontrado.");
    }
}