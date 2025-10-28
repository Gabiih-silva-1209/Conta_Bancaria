package com.senai.Conta_Bancaria.domain.exception;

public class ValoresNegativosException extends RuntimeException {
    public ValoresNegativosException(String operacao) {
        String message = "Não é possível realizar a operação de " + operacao + " com valores negativos.";
    }
}

