package br.com.microservices.mscartoes.service;

import br.com.microservices.mscartoes.dto.clienteCartao.ClienteCartaoOut;
import br.com.microservices.mscartoes.entity.ClienteCartao;
import br.com.microservices.mscartoes.repository.ClienteCartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {
    private final ClienteCartaoRepository repository;

    public List<ClienteCartaoOut> listCartoesByCpf(String cpf) {
        var clienteCartao = repository.findByCpf(cpf);
        return clienteCartao.stream().map(ClienteCartaoOut::fromEntity).toList();
    }
}
