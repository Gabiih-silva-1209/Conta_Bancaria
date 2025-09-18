package com.senai.Conta_Bancaria.application.dto;

import java.math.BigDecimal;

public record ContaResumoDTO(String numeroDaConta,
                             String Tipo,
                             BigDecimal Saldo) {

public Conta toEntity (CLiente cliente){
if("CORRENTE".equalsIgnoreCase(tipo)){
    return ContaCorrente.builder()
            .cliente(cliente)
            .numeroDaConta(numeroDaConta)
            .saldo(saldo)
            .ativa(true)
            .build();
} else if ("POUPANÇA".equalsIgnoreCase(tipo)) {
    return ContaPoupanca.builder()
            .cliente(cliente)
            .numeroDaConta(numeroDaConta)
            .saldo(saldo)
            .ativa(true)
            .build();

}
}

}
