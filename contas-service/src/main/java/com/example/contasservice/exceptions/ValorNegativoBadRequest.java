package com.example.contasservice.exceptions;

public class ValorNegativoBadRequest extends Exception {
    public ValorNegativoBadRequest(String property) {
        super(property + " negativo não é permitido");
    }
}
