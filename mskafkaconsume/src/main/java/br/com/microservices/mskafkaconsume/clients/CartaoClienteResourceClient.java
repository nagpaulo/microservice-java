package br.com.microservices.mskafkaconsume.clients;

import br.com.microservices.mskafkaconsume.dto.DadosSolicitacaoEmissaoCartao;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoClienteResourceClient {

    @PostMapping("/solicitar-cartao")
    void solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados);
}
