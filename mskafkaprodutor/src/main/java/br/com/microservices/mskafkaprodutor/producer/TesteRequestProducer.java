package br.com.microservices.mskafkaprodutor.producer;

import br.com.microservices.mskafkaprodutor.dto.TesteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TesteRequestProducer {

    @Value("${spring.kafka.model.topic}")
    private String topicTesteRequest;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendMensagem(TesteDTO testeDTO) throws JsonProcessingException {
        String conteudo = objectMapper.writeValueAsString(testeDTO);
        kafkaTemplate.send(topicTesteRequest, conteudo);
        return "Teste do topico enviado para processamento";
    }
}
