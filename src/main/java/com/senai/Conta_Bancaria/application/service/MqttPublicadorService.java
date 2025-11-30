package com.senai.Conta_Bancaria.application.service;

import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPublisher;
import org.springframework.stereotype.Service;

@Service
public class MqttPublicadorService {

    @MqttPublisher("conta_bancaria/pagamentos")
    public String enviarMensagem(String mensagem) {
        return mensagem;
    }
}
