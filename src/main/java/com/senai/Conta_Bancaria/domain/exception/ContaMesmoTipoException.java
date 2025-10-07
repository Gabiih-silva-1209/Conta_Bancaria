package com.senai.Conta_Bancaria.domain.exception;

public class ContaMesmoTipoException extends RuntimeException {
    public ContaMesmoTipoException() {
        super("Não é possível criar uma conta do mesmo tipo para o cliente.");
    }
}
