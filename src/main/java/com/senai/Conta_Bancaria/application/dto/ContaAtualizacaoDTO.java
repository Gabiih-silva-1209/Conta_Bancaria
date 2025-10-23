package com.senai.Conta_Bancaria.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ContaAtualizacaoDTO(
        @NotNull(message = "O saldo não pode ser nulo")
        @Positive(message = "O saldo deve ser um valor positivo")
        BigDecimal saldo,

        @NotNull(message = "O limite não pode ser nulo")
        @Positive(message = "O limite deve ser um valor positivo")
        BigDecimal limite,
//esqueci minha conta sou boba

        @NotNull(message = "O rendimento não pode ser nulo")
        @Positive(message = "O rendimento deve ser um valor positivo")
        @DecimalMin(value = "0.0", message = "O rendimento deve ser maior ou igual a 0")
        @Max(value = 100, message = "O rendimento não pode ser maior que 100")
        BigDecimal rendimento,
        @NotNull(message = "A taxa não pode ser nula")
        @Positive(message = "A taxa deve ser um valor positivo")
        BigDecimal taxa
) {
}
