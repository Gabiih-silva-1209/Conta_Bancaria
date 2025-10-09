package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Cliente;
import com.senai.Conta_Bancaria.domain.entity.Conta;
import com.senai.Conta_Bancaria.domain.entity.ContaCorrente;
import com.senai.Conta_Bancaria.domain.entity.ContaPoupanca;
import com.senai.Conta_Bancaria.domain.exception.TipoDeContaInvalidaException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ContaResumoDTO(
        @Size(min = 5, max = 20, message = "O número da conta deve ter entre 5 e 20 caracteres")
        String numero,
        @NotNull(message = "O tipo da conta não pode estar vazio")
        String tipo,
        @NotNull(message = "O saldo não pode ser nulo")
        @Positive(message = "O saldo deve ser positivo")
        BigDecimal saldo
) {

    public Conta toEntity(Cliente cliente) {
        if ("CORRENTE".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .taxa(new BigDecimal(0.05))
                    .limite(new BigDecimal(500))
                    .ativa(true)
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaPoupanca.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .rendimento(new BigDecimal(0.01))
                    .ativa(true)
                    .build();
        }
        throw new TipoDeContaInvalidaException(tipo);
    }

    public static ContaResumoDTO fromEntity(Conta conta) {
        return new ContaResumoDTO(
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo()
        );
    }
}
