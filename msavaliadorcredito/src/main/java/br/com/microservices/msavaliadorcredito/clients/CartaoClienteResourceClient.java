package br.com.microservices.msavaliadorcredito.clients;

import br.com.microservices.msavaliadorcredito.dto.ClienteCartaoOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CartaoClienteResourceClient {
    @GetMapping(params = "cpf")
    List<ClienteCartaoOut> getCartoesByCliente(@RequestParam("cpf") String cpf);
}
