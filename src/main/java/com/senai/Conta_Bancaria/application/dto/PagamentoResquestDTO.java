package com.senai.Conta_Bancaria.application.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.Set;


public record PagamentoResquestDTO (

        @NotNull(message = "O ID da conta não pode ser nulo")
                Long contaId,

 @NotBlank(message = "O código do boleto não pode estar vazio")
 @Size(min = 5, max = 50, message = "O boleto deve ter entre 5 e 50 dígitos")
 String boleto,

 @NotNull(message = "O valor não pode ser nulo")
 @Positive(message = "O valor deve ser positivo")
 BigDecimal valor,

 @NotNull(message = "A lista de taxas não pode ser nula")
 Set<Long> taxasIds

) {}

