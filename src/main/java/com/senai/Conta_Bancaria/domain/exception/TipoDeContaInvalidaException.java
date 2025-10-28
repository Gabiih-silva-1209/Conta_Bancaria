package com.senai.Conta_Bancaria.domain.exception;

public class TipoDeContaInvalidaException extends RuntimeException {
    public TipoDeContaInvalidaException(String tipo) {
        super("Tipo de conta inválida." + tipo+ "Os tipos válidos são: 'CORRENTE' ou 'POUPANCA'.");
    }
}

