package com.senai.Conta_Bancaria.domain.exception;

public class ContaMesmoTipoException extends RuntimeException {
    public ContaMesmoTipoException() {
        super("O cliente já possui uma conta deste tipo.");
    }
}
