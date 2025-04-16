package com.fatinfo.clientecomandante;

import java.util.UUID;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando cliente MQTT...");

        Mqtt3AsyncClient client = MqttClient.builder()
        .useMqttVersion3()
        .identifier(UUID.randomUUID().toString())
        .serverHost("06c5f64164d14759bb3b8c2d6b4bb33c.s1.eu.hivemq.cloud")
        .serverPort(8883) 
        .sslWithDefaultConfig()
        .buildAsync();

        client.connectWith()
        .simpleAuth()
            .username("admin")
            .password("Fat123456".getBytes())
            .applySimpleAuth()
        .send()
        .whenComplete((v, e) -> {
            if (e != null) {
                System.out.println("Erro ao conectar ao broker MQTT: " + e.getMessage());
            } else {
                System.out.println("Conectado ao broker MQTT com sucesso!");

                client.publishWith()
                    .topic("esp32")
                    .payload("Servidor comandante conectado!".getBytes())
                    .send()
                    .whenComplete((v2, e2) -> {
                        if (e2 != null) {
                            System.out.println("Erro ao publicar mensagem: " + e2.getMessage());
                        } else {
                            System.out.println("Mensagem publicada com sucesso!");
                        }
                    });
            }
        });

        client.subscribeWith()
            .topicFilter("esp32")
            .callback(msg -> System.out.println("Mensagem recebida: " + new String(msg.getPayloadAsBytes())))
            .send()
        .whenComplete((v, e) -> {
            if (e != null) {
                System.out.println("Erro ao se inscrever no tópico: " + e.getMessage());
            } else {
                System.out.println("Se inscreveu no tópico com sucesso!");
            }
        });
    }
}