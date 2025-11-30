package com.senai.Conta_Bancaria.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public record PagamentoResponseDTO(

        @NotNull(message = "O ID do pagamento não pode ser nulo")
        Long id,

        @NotBlank(message = "O código do boleto não pode estar vazio")
        @Size(min = 5, max = 50, message = "O boleto deve ter entre 5 e 50 dígitos")
        String boleto,

        @NotNull(message = "O valor pago não pode ser nulo")
        @Positive(message = "O valor pago deve ser positivo")
        BigDecimal valorPago,

        @NotNull(message = "A data de pagamento não pode ser nula")
        LocalDateTime dataPagamento,

        @NotBlank(message = "O status não pode estar vazio")
        String status,

        @NotNull(message = "A lista de taxas não pode ser nula")
        Set<String> taxasDescricao
) {
    // método auxiliar opcional: converter da entidade Pagamento para o DTO
    public static PagamentoResponseDTO fromEntity(com.senai.Conta_Bancaria.domain.entity.Pagamento pagamento) {
        Set<String> taxasDesc = pagamento.getTaxa() == null ? Set.of() :
                pagamento.getTaxa().stream()
                        .map(t -> t.getDescricao() != null ? t.getDescricao() : t.getId().toString())
                        .collect(java.util.stream.Collectors.toSet());

        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getBoleto(),
                pagamento.getValorPago(),
                pagamento.getDataPagamento(),
                pagamento.getStatus(),
                taxasDesc
        );
    }
}
