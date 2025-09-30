package com.senai.Conta_Bancaria.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("CORRENTE")

public class ContaCorrente extends Conta {

    @Column(precision = 4) // quantidade de dígitos
    private BigDecimal limite;

    @Column(precision = 5)
    private BigDecimal taxa;

    @Override
    public String getTipo() {
        return "CORRENTE";
    }

    @Override
    public void sacar(BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO)<0)
            throw new IllegalArgumentException("Valor inválido para saque");
        BigDecimal custoSaque = valor.multiply(taxa); //multiply é multiplicado
        BigDecimal totalSaque = valor.add(custoSaque);
        if(getSaldo().add(limite).compareTo(totalSaque)<0)
            throw new IllegalArgumentException("Saldo insuficiente para saque");

        setSaldo(getSaldo().subtract(totalSaque));
}
        }
