package br.com.microservices.mskafkaprodutor.controller;

import br.com.microservices.mskafkaprodutor.dto.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mskafkaprodutor.dto.TesteDTO;
import br.com.microservices.mskafkaprodutor.service.EmissaoCartaoService;
import br.com.microservices.mskafkaprodutor.service.TesteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka-producer")
@RequiredArgsConstructor
public class ProducerKafkaController {

    private final TesteService testeService;
    private final EmissaoCartaoService emissaoCartaoService;

    @GetMapping
    public String status(){
        return "Ok!";
    }

    @PostMapping("/teste")
    public String testeKafka (@RequestBody TesteDTO testeDTO) {
        return testeService.integrarTeste(testeDTO);
    }

    @PostMapping("/emissao-cartao")
    public String EmissaoCartaoKafka (@RequestBody DadosSolicitacaoEmissaoCartao dadosSolicitacaoEmissaoCartao) {
        return emissaoCartaoService.integrarEmissaoCartao(dadosSolicitacaoEmissaoCartao);
    }
}
