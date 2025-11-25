package com.senai.Conta_Bancaria.application.service;
package com.senai.Conta_Bancaria.domain.pagamento;

import com.senai.Conta_Bancaria.domain.taxa.Taxa;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PagamentoDomainService {
}


public class PagamentoDomainService {

    /**
     * Calcula o valor total das taxas relativas a um valor base.
     * @param valorBase valor do pagamento
     * @param taxas conjunto de taxas aplicáveis
     * @return soma das taxas (valor fixo + percentual)
     */
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

