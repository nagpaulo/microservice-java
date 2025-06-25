package br.com.microservices.msavaliadorcredito.controller;

import br.com.microservices.msavaliadorcredito.dto.SituacaoClienteDto;
import br.com.microservices.msavaliadorcredito.exceptions.ResourceNotFoundException;
import br.com.microservices.msavaliadorcredito.service.AvaliadorCreditoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService service;

    @GetMapping
    public String status(){
        return "Ok!";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public SituacaoClienteDto consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            return service.obterSituacaoCliente(cpf);
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("O clinete com CPF: " + cpf + " não foi encontrado.");
        } catch (FeignException.InternalServerError e) {
            throw new RuntimeException(e);
        }
    }

}
