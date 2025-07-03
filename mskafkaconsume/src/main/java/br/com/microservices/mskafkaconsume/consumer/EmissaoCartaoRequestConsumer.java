package br.com.microservices.mskafkaconsume.consumer;

import br.com.microservices.mskafkaconsume.clients.AvaliadorCreditoResouceClient;
import br.com.microservices.mskafkaconsume.clients.CartaoClienteResourceClient;
import br.com.microservices.mskafkaconsume.dto.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mskafkaconsume.dto.ProtocoloSolicitacaoCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmissaoCartaoRequestConsumer {

    private final AvaliadorCreditoResouceClient avaliadorCreditoResouceClient;
    private final CartaoClienteResourceClient cartaoClienteResourceClient;

    @KafkaListener(
            topics = "${spring.kafka.topic.emissao-cartoes}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void solicitarCartao(String message) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao = mapper.readValue(message, DadosSolicitacaoEmissaoCartao.class);
            ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoResouceClient.solicitarCartao(dadosSolicitacaoEmissaoCartao);
            cartaoClienteResourceClient.solicitarCartao(dadosSolicitacaoEmissaoCartao);
            System.out.println("=== MENSAGEM RECEBIDA - EMISSAO CARTAO === CPF: " + dadosSolicitacaoEmissaoCartao.getCpf());
            System.out.println("=== MENSAGEM RECEBIDA - EMISSAO CARTAO === Protocolo: " + protocoloSolicitacaoCartao.getProtocolo());
            System.out.println("=== MENSAGEM RECEBIDA - EMISSAO CARTAO ===" + message);
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("O cartão ou cliente não foi encontrado.");
        } catch (FeignException.InternalServerError e) {
            throw new RuntimeException(e);
        }
    }
}
