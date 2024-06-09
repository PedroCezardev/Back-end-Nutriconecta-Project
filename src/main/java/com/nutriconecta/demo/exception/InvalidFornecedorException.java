package com.nutriconecta.demo.exception;

public class InvalidFornecedorException extends RuntimeException {
    public InvalidFornecedorException (String message) {
        super(message);
    }

    public InvalidFornecedorException () {
        super("Fornecedor procurado nao encontrado.");
    }
}