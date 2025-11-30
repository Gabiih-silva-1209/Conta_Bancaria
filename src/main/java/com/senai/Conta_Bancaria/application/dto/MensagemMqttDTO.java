package com.senai.Conta_Bancaria.application.dto;

import com.senai.Conta_Bancaria.domain.entity.Mensagem;
import lombok.Builder;

@Builder
public record MensagemMqttDTO(
        String nome,
        String conteudo
) {
    public static Mensagem toEntity(MensagemMqttDTO mensagemMqttDTO) {
        return Mensagem.builder()
                .nome(mensagemMqttDTO.nome())
                .conteudo(mensagemMqttDTO.conteudo())
                .build();
    }
}