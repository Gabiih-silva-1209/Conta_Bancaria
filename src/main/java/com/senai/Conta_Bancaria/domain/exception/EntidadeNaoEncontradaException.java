package com.senai.Conta_Bancaria.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(entidade+ " não existente ou inativo (a).");
    }
}
//esqueci minha conta sou boba
