package br.com.microservices.mskafkaprodutor.service;

import br.com.microservices.mskafkaprodutor.dto.TesteDTO;
import br.com.microservices.mskafkaprodutor.producer.TesteRequestProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TesteService {
    private final TesteRequestProducer testeRequestProducer;

    public String integrarTeste (TesteDTO testeDTO) {
        try {
            return testeRequestProducer.sendMensagem(testeDTO);
        } catch (JsonProcessingException e) {
            return "Houve um erro ao solicitar pagamento "+e.getMessage();
        }
    }
}
