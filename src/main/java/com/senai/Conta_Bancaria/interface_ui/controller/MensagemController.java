package com.senai.Conta_Bancaria.interface_ui.controller;

import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPayload;
import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPublisher;
import com.rafaelcosta.spring_mqttx.domain.annotation.MqttSubscriber;
import com.senai.teste_mqtt.application.dto.MensagemMqttDTO;
import com.senai.teste_mqtt.application.dto.MensagenRestDTO;
import com.senai.teste_mqtt.application.service.MensagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;
    @PostMapping
    @MqttPublisher("topico/mensagem/rest")
    public MensagemMqttDTO  enviarMensagem(@RequestBody MensagenRestDTO mensagemRestDTO) {

        return mensagemService.enviarMensagem(mensagemRestDTO);
    }

    @MqttSubscriber("topico/mensagem/mqtt")
    public void receberMensagem(@MqttPayload  MensagemMqttDTO mensagemMqttDTO) {
        mensagemService.salvarMensagemRecebida(mensagemMqttDTO);
    }
}
