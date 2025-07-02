package br.com.microservices.mskafkaconsume.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TesteRequestConsumer {

    @KafkaListener(
            topics = "${spring.kafka.model.topic}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(String message) {
        System.out.println("=== MENSAGEM RECEBIDA ===" + message);
    }
}
