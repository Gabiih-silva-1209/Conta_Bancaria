package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Cliente;
import com.senai.Conta_Bancaria.domain.entity.Conta;
import com.senai.Conta_Bancaria.domain.entity.ContaCorrente;
import com.senai.Conta_Bancaria.domain.entity.ContaPoupanca;

import java.math.BigDecimal;

public record ContaResumoDTO(String numeroDaConta,
                             String Tipo,
                             BigDecimal Saldo
) {

    public Conta toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(Tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numeroDaConta(this.numeroDaConta)
                    .saldo(this.Saldo)
                    .ativa(true)
                    .build();
        } else if ("POUPANÇA".equalsIgnoreCase(Tipo)) {
            return ContaPoupanca.builder()
                    .cliente(cliente)
                    .numeroDaConta(this.numeroDaConta)
                    .saldo(this.Saldo)
                    .ativa(true)
                    .build();

        }
        return null;
    }

    public static ContaResumoDTO fromEntity(Conta conta) {
        return new ContaResumoDTO(
                conta.getNumeroDaConta(),
                conta.getTipo(),
                conta.getSaldo()
        );
    }
}
