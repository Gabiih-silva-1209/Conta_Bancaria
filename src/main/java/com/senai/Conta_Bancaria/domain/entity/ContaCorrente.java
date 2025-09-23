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
}