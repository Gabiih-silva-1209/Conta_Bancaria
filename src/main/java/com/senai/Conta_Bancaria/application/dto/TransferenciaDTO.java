package com.senai.Conta_Bancaria.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransferenciaDTO(
        @NotBlank(message = "A conta destino não pode estar vazia")
        @Size(min = 5, max = 20, message = "O número da conta destino deve ter entre 5 e 20 caracteres")
        String contaDestino,
        @NotNull(message = "O valor de transferência não pode ser nulo")
        @Positive(message = "O valor de transferência deve ser positivo")
        BigDecimal valor
                                ) {

}
//esqueci minha conta sou boba
