package br.com.microservices.mscartoes.controller;

import br.com.microservices.mscartoes.dto.cartao.CartaoIn;
import br.com.microservices.mscartoes.dto.cartao.CartaoOut;
import br.com.microservices.mscartoes.dto.clienteCartao.ClienteCartaoOut;
import br.com.microservices.mscartoes.service.CartaoService;
import br.com.microservices.mscartoes.service.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesController {

    private final CartaoService service;
    private final ClienteCartaoService clienteCartaoService;

    @GetMapping
    public String status() {
        return "Ok!";
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CartaoOut cadastrar(@RequestBody CartaoIn cartao) {
        return service.save(cartao);
    }

    @GetMapping(params = "renda")
    public List<CartaoOut> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        return service.getCartoesRendaMenorIgual(renda);
    }

    @GetMapping(params = "cpf")
    public List<ClienteCartaoOut> getCartoesByCliente(@RequestParam("cpf") String cpf) {
        return clienteCartaoService.listCartoesByCpf(cpf);
    }
}
