package com.senai.Conta_Bancaria.application.service;

import com.senai.Conta_Bancaria.domain.entity.Pagamento;
import com.senai.Conta_Bancaria.domain.entity.Taxa;
import com.senai.Conta_Bancaria.domain.exception.SaldoInsuficienteException;
import com.senai.Conta_Bancaria.domain.repository.ContaRepository;
import com.senai.Conta_Bancaria.domain.repository.PagamentoRepository;
import com.senai.Conta_Bancaria.repository.PagamentoRepository;
import com.senai.Conta_Bancaria.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Set;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ContaRepository contaRepository;
    private final MqttPublicadorService mqttPublicadorService;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            ContaRepository contaRepository,
                            MqttPublicadorService mqttPublicadorService) {
        this.pagamentoRepository = pagamentoRepository;
        this.contaRepository = contaRepository;
        this.mqttPublicadorService = mqttPublicadorService;
    }

    /**
     * Calcula o valor final do pagamento somando percentuais (convertidos de % para fração)
     * e valores fixos das taxas.
     */
    public BigDecimal calcularValorFinal(Pagamento pagamento) {
        BigDecimal valorBase = pagamento.getValorPago();
        if (valorBase == null) valorBase = BigDecimal.ZERO;

        BigDecimal valorFinal = valorBase;

        Set<Taxa> taxas = pagamento.getTaxa();
        if (taxas == null || taxas.isEmpty()) return valorFinal;

        for (Taxa taxa : taxas) {
            // taxa.getPercentual() deve ser BigDecimal representando percentual (ex.: 5 para 5%)
            BigDecimal percentual = BigDecimal.ZERO;
            if (taxa.getPercentual() != null) {
                // converter percentual (ex.: 5) para fração (0.05)
                percentual = valorBase.multiply(
                        taxa.getPercentual()
                                .divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP)
                );
            }

            BigDecimal valorFixo = taxa.getValorFixo() != null ? taxa.getValorFixo() : BigDecimal.ZERO;

            valorFinal = valorFinal.add(percentual).add(valorFixo);
        }

        // ajustar scale (opcional) para 2 casas decimais
        return valorFinal.setScale(2, RoundingMode.HALF_UP);
    }

    public void validarSaldo(Pagamento pagamento) {
        BigDecimal valorFinal = calcularValorFinal(pagamento);

        BigDecimal saldo = pagamento.getConta().getSaldo() != null ? pagamento.getConta().getSaldo() : BigDecimal.ZERO;

        if (saldo.compareTo(valorFinal) < 0) {
            throw new SaldoInsuficienteException("Pagamento não realizado: saldo insuficiente");
        }
    }

    /**
     * Efetua o pagamento: valida saldo, debita a conta, salva o pagamento e publica mensagem via MQTT.
     */
    @Transactional
    public Pagamento efetuarPagamento(Pagamento pagamento) {
        // validações iniciais
        validarSaldo(pagamento);

        BigDecimal valorFinal = calcularValorFinal(pagamento);

        // debitar saldo da conta
        var conta = pagamento.getConta();
        BigDecimal novoSaldo = conta.getSaldo().subtract(valorFinal).setScale(2, RoundingMode.HALF_UP);
        conta.setSaldo(novoSaldo);
        contaRepository.save(conta);

        // completar dados do pagamento
        pagamento.setStatus("SUCESSO");
        pagamento.setDataPagamento(LocalDateTime.now());
        pagamento.setValorPago(pagamento.getValorPago()); // já deve estar setado no DTO

        // salvar pagamento
        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        // publicar mensagem no MQTT (após salvar)
        try {
            String mensagem = "Pagamento realizado! ID=" + pagamentoSalvo.getId()
                    + " ValorTotal=" + valorFinal.toPlainString()
                    + " ContaId=" + conta.getId();
            mqttPublicadorService.enviarMensagem(mensagem);
        } catch (Exception e) {
            // logar o erro de publicação (não reverter a transação por falha no envio MQTT)
            // use seu logger preferido (ex.: SLF4J). Exemplo simples:
            System.err.println("Falha ao publicar mensagem MQTT: " + e.getMessage());
        }

        return pagamentoSalvo;
    }
}
