package br.com.microservices.msavaliadorcredito.clients;

import br.com.microservices.msavaliadorcredito.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

    @GetMapping(params = "cpf")
    Optional<ClienteDto> dadosClientes(@RequestParam("cpf") String cpf);
}
