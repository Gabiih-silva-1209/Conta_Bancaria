package com.senai.Conta_Bancaria.domain.exception;

public class TransferirParaMesmaContaException extends RuntimeException {
    public TransferirParaMesmaContaException() {
        super("Não é possível transferir para a mesma conta.");
    }
}
//esqueci minha conta sou boba
