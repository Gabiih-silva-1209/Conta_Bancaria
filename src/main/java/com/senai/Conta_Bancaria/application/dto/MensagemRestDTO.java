package com.senai.Conta_Bancaria.application.dto;

import lombok.Builder;

@Builder
public record MensagenRestDTO(
        String nome,
        String conteudo
) {
}