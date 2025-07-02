package br.com.microservices.mskafkaconsume.consumer;

import br.com.microservices.mskafkaconsume.clients.AvaliadorCreditoResouceClient;
import br.com.microservices.mskafkaconsume.dto.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mskafkaconsume.dto.ProtocoloSolicitacaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmissaoCartaoRequestConsumer {

    private final AvaliadorCreditoResouceClient avaliadorCreditoResouceClient;

    @KafkaListener(
            topics = "${spring.kafka.topic.emissao-cartoes}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void solicitarCartao(String message) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao = mapper.readValue(message, DadosSolicitacaoEmissaoCartao.class);
        ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoResouceClient.solicitarCartao(dadosSolicitacaoEmissaoCartao);
        System.out.println("=== CPF - EMISSAO CARTAO ===" + dadosSolicitacaoEmissaoCartao.getCpf());
        System.out.println("=== CPF - EMISSAO CARTAO === Protocolo: " + protocoloSolicitacaoCartao.getProtocolo());
        System.out.println("=== MENSAGEM RECEBIDA - EMISSAO CARTAO ===" + message);
    }
}
