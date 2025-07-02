package br.com.microservices.mskafkaprodutor.producer;

import br.com.microservices.mskafkaprodutor.dto.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mskafkaprodutor.dto.TesteDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmissaoCartaoRequestProducer {

    @Value("${spring.kafka.topic.emissao-cartoes}")
    private String topicEmissaoCartaoRequest;

    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public String sendMensagem(DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) throws JsonProcessingException {
        String conteudo = objectMapper.writeValueAsString(dadosSolicitacaoEmissaoCartao);
        kafkaTemplate.send(topicEmissaoCartaoRequest, conteudo);
        return "Emissao Cartao teste do topico enviado para processamento";
    }
}
