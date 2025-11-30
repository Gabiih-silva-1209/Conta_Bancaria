package com.senai.Conta_Bancaria.application.service;

import com.senai.Conta_Bancaria.application.dto.MensagemMqttDTO;
import com.senai.Conta_Bancaria.application.dto.MensagenRestDTO;
import com.senai.Conta_Bancaria.domain.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    public MensagemMqttDTO enviarMensagem(MensagenRestDTO mensagemRestDTO) {
        return MensagemMqttDTO.builder()
                .nome(mensagemRestDTO.nome())
                .conteudo(mensagemRestDTO.conteudo())
                .build();
    }

    public void salvarMensagemRecebida(MensagemMqttDTO mensagemMqttDTO) {

        mensagemRepository.save(MensagemMqttDTO.toEntity(mensagemMqttDTO));
    }
}
