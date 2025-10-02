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

    @Column(precision = 19, scale= 2) // quantidade de dígitos
    private BigDecimal limite;

    @Column(precision = 19, scale= 2) // presecion: quantidade de dígitos e scale: quantidade de números ápos a vírgula
    private BigDecimal taxa;

    @Override
    public String getTipo() {
        return "CORRENTE";
    }

    @Override
    public void sacar(BigDecimal valor){
        validarValorMaiorQueZero(valor);

        BigDecimal custoSaque = valor.multiply(taxa);
        BigDecimal totalSaque = valor.add(custoSaque);
if (this.getSaldo().add(this.limite).compareTo(totalSaque)<0){
    throw new IllegalArgumentException("Saldo insuficiente para saque" );
}
        setSaldo(this.getSaldo().subtract(totalSaque));
}
        }
