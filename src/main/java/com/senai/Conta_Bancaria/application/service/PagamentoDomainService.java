package com.senai.Conta_Bancaria.application.service;


import com.senai.Conta_Bancaria.domain.entity.Taxa;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PagamentoDomainService {

    public double calcularTotalTaxas(double valorBase, Set<Taxa> taxas) {
        double total = 0.0;
        if (taxas == null) return 0.0;
        for (Taxa t : taxas) {
            if (t.getValorFixo() != null) {
                total += t.getValorFixo();
            }
            if (t.getPercentual() != null) {
                total += (valorBase * t.getPercentual() / 100.0);
            }
        }
        return total;
    }
}

