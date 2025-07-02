package br.com.microservices.mskafkaconsume.clients;


import br.com.microservices.mskafkaconsume.dto.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mskafkaconsume.dto.ProtocoloSolicitacaoCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "msavaliadorcredito", path = "/avaliacoes-credito")
public interface AvaliadorCreditoResouceClient {

    @PostMapping("/solicitar-cartao")
    ProtocoloSolicitacaoCartao solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados);
}
