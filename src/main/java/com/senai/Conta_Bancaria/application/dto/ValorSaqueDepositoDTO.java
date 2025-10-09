package com.senai.Conta_Bancaria.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ValorSaqueDepositoDTO(
        @NotNull(message = "O valor não pode ser nulo")
        @Positive(message = "O valor deve ser positivo")
        @PositiveOrZero(message = "O valor deve ser zero ou positivo")
        BigDecimal valor
) {
}
