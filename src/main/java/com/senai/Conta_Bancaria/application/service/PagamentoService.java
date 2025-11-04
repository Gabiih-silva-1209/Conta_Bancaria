package com.senai.Conta_Bancaria.application.service;

import com.senai.Conta_Bancaria.domain.entity.Pagamento;
import com.senai.Conta_Bancaria.domain.entity.Taxa;
import com.senai.Conta_Bancaria.domain.exception.SaldoInsuficienteException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PagamentoService {
    public BigDecimal calcularValorFinal(Pagamento pagamento) {
        BigDecimal valorFinal = pagamento.getValorPago();

        for (Taxa taxa : pagamento.getTaxa()) {
BigDecimal percentual = pagamento.getValorPago().multiply(taxa.getPercentual());
valorFinal = valorFinal.add(percentual).add(taxa.getValorFixo());
        }
        return valorFinal;
    }
    public void validarSaldo(Pagamento pagamento){
        BigDecimal valorFinal = calcularValorFinal(pagamento);

        if (pagamento.getConta().getSaldo().compareTo(valorFinal)<0){
            throw new SaldoInsuficienteException("Pagamento não realizado: saldo insuficiente");
        }
    }
    public void efetuarPagamento(Pagamento pagamento){
        validarSaldo(pagamento);
        BigDecimal valorFinal = calcularValorFinal(pagamento);

        pagamento.getConta().setSaldo(
                pagamento.getConta().getSaldo().subtract(valorFinal)
        );
        pagamento.setStatus("Sucesso");
        pagamento.setDataPagamento(LocalDateTime.now());
    }
}
