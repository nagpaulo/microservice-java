package br.com.microservices.mskafkaprodutor.service;

import br.com.microservices.mskafkaprodutor.dto.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mskafkaprodutor.dto.TesteDTO;
import br.com.microservices.mskafkaprodutor.producer.EmissaoCartaoRequestProducer;
import br.com.microservices.mskafkaprodutor.producer.TesteRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmissaoCartaoService {
    private final EmissaoCartaoRequestProducer emissaoCartaoRequestProducer;

    public String integrarEmissaoCartao (DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) {
        try {
            return emissaoCartaoRequestProducer.sendMensagem(dadosSolicitacaoEmissaoCartao);
        } catch (JsonProcessingException e) {
            return "Houve um erro ao solicitar pagamento "+e.getMessage();
        }
    }
}
