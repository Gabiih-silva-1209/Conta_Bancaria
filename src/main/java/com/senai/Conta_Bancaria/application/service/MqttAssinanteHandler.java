package com.senai.Conta_Bancaria.application.service;

import com.rafaelcosta.spring_mqttx.domain.annotation.MqttSubscriber;
import com.rafaelcosta.spring_mqttx.domain.annotation.MqttPayload;
import org.springframework.stereotype.Component;

@Component
public class MqttAssinanteHandler {

    @MqttSubscriber("conta_bancaria/pagamentos")
    public void receberMensagem(@MqttPayload String mensagem) {
        System.out.println("📩 Mensagem recebida no MQTT: " + mensagem);
    }
}

