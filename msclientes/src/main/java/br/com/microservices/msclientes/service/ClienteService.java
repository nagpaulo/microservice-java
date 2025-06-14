package br.com.microservices.msclientes.service;

import br.com.microservices.msclientes.dto.cliente.ClienteInDto;
import br.com.microservices.msclientes.dto.cliente.ClienteOutDto;
import br.com.microservices.msclientes.entity.Cliente;
import br.com.microservices.msclientes.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteOutDto save(ClienteInDto cliente) {
        Cliente clienteEntity = Cliente.builder()
                .idade(cliente.getIdade())
                .cpf(cliente.getCpf())
                .nome(cliente.getNome())
                .build();
        Cliente cl = repository.save(clienteEntity);
        return ClienteOutDto.builder()
                .nome(cl.getNome())
                .idade(cl.getIdade())
                .cpf(cl.getCpf())
                .build();
    }

    public Optional<Cliente> getByCPF(String cpf) {
        return repository.findByCpf(cpf);
    }
}
