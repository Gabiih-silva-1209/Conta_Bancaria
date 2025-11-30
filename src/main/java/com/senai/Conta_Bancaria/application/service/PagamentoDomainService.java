package com.senai.Conta_Bancaria.application.service;

import com.senai.Conta_Bancaria.domain.entity.Taxa;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class PagamentoDomainService {

    public BigDecimal calcularTotalTaxas(BigDecimal valorBase, Set<Taxa> taxas) {

        if (valorBase == null) {
            throw new IllegalArgumentException("O valor base não pode ser nulo.");
        }

        BigDecimal total = BigDecimal.ZERO;

        if (taxas == null || taxas.isEmpty()) {
            return BigDecimal.ZERO;
        }

        for (Taxa t : taxas) {

            // Soma valor fixo, se existir
            if (t.getValorFixo() != null) {
                total = total.add(t.getValorFixo());
            }

            // Soma percentual, se existir
            if (t.getPercentual() != null) {
                BigDecimal percentual = valorBase
                        .multiply(t.getPercentual())   // valorBase * percentual
                        .divide(BigDecimal.valueOf(100)); // /100
                total = total.add(percentual);
            }
        }

        return total;
    }
}
