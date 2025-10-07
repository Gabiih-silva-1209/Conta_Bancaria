package com.senai.Conta_Bancaria.domain.exception;

public class SaldoInsuficienteException extends RuntimeException {
    public SaldoInsuficienteException(String message) {

        super(message);
    }
}
