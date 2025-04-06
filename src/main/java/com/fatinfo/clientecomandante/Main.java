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
        .serverHost("broker.hivemq.com")
        .serverPort(1883)
        .buildAsync();

        

    }
}