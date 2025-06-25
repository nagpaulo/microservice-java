package br.com.microservices.msavaliadorcredito.service;

import br.com.microservices.msavaliadorcredito.clients.CartaoClienteResourceClient;
import br.com.microservices.msavaliadorcredito.clients.ClienteResourceClient;
import br.com.microservices.msavaliadorcredito.dto.*;
import br.com.microservices.msavaliadorcredito.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

    private final ClienteResourceClient clienteResourceClient;
    private final CartaoClienteResourceClient cartaoClienteResourceClient;

    private static CartaoClienteDto apply(ClienteCartaoOut clienteCartao) {
        return CartaoClienteDto.builder()
                .nome(clienteCartao.getCartao().getNome())
                .bandeira(clienteCartao.getCartao().getBandeira())
                .limiteLiberado(clienteCartao.getLimite())
                .build();
    }

    public SituacaoClienteDto obterSituacaoCliente(String cpf) {

        ClienteDto clienteDto = clienteResourceClient.dadosClientes(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o CPF informado."));
        DadosClienteDto dadosClienteDto = DadosClienteDto.builder()
                .id(clienteDto.getId())
                .nome(clienteDto.getNome())
                .build();

        List<ClienteCartaoOut> clienteCartaoOut = cartaoClienteResourceClient.getCartoesByCliente(cpf);
        List<CartaoClienteDto> listCartaoCliente = clienteCartaoOut.stream().map(AvaliadorCreditoService::apply).toList();

        return SituacaoClienteDto.builder()
                .cliente(dadosClienteDto)
                .cartoes(listCartaoCliente)
                .build();

    }
}
