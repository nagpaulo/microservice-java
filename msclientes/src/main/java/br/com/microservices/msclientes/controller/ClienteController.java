package br.com.microservices.msclientes.controller;

import br.com.microservices.msclientes.dto.cliente.ClienteInDto;
import br.com.microservices.msclientes.dto.cliente.ClienteOutDto;
import br.com.microservices.msclientes.entity.Cliente;
import br.com.microservices.msclientes.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public String status(){
        return "Ok!";
    }


    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ClienteInDto clienteInDto) {
        service.save(clienteInDto);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(clienteInDto.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    @ResponseStatus(HttpStatus.OK)
    public Cliente dadosClientes(@RequestParam("cpf") String cpf) {
        return service.getByCPF(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }
}
