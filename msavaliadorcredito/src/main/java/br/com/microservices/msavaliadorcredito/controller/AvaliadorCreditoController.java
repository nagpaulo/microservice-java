package br.com.microservices.msavaliadorcredito.controller;

import br.com.microservices.msavaliadorcredito.dto.*;
import br.com.microservices.msavaliadorcredito.exceptions.ErroSolicitarCartaoException;
import br.com.microservices.msavaliadorcredito.exceptions.ResourceNotFoundException;
import br.com.microservices.msavaliadorcredito.service.AvaliadorCreditoService;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            throw new ResourceNotFoundException("O cliente com CPF: " + cpf + " não foi encontrado.");
        } catch (FeignException.InternalServerError e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public RetornoAvaliacaoClienteOut realizarAvaliacao (@RequestBody DadosAvaliacaoDto dados) {
        try {
            return service.realizarAvaliacao(dados.getCpf(), dados.getRenda());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("O cliente com CPF: " + dados.getCpf() + " não foi encontrado.");
        } catch (FeignException.InternalServerError e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/solicitar-cartao")
    public ProtocoloSolicitacaoCartao solicitarCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
        try {
            return service.solicitacaoCartao(dados);
        } catch (ErroSolicitarCartaoException e) {
            throw new ErroSolicitarCartaoException(e.getMessage());
        } catch (FeignException.NotFound ex) {
            throw new ResourceNotFoundException("O cartão com ID: " + dados.getIdCartao() + "do cliente com CPF: " + dados.getCpf() + " não foi encontrado.");
        } catch (FeignException.InternalServerError e) {
            throw new RuntimeException(e);
        }
    }

}
