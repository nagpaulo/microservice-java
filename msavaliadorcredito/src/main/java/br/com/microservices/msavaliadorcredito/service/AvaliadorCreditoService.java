package br.com.microservices.msavaliadorcredito.service;

import br.com.microservices.msavaliadorcredito.clients.CartaoClienteResourceClient;
import br.com.microservices.msavaliadorcredito.clients.ClienteResourceClient;
import br.com.microservices.msavaliadorcredito.dto.*;
import br.com.microservices.msavaliadorcredito.exceptions.ErroSolicitarCartaoException;
import br.com.microservices.msavaliadorcredito.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

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

    public RetornoAvaliacaoClienteOut realizarAvaliacao(String cpf, Long renda) {
        ClienteDto clienteDto = clienteResourceClient.dadosClientes(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado para o CPF informado."));

        DadosClienteDto dadosClienteDto = DadosClienteDto.builder()
                .id(clienteDto.getId())
                .nome(clienteDto.getNome())
                .idade(clienteDto.getIdade())
                .build();

        List<CartaoOut> cartoes = cartaoClienteResourceClient.getCartoesRendaAteh(renda);
        List<CartaoAprovadoDto> listCartaoAprovado = cartoes.stream().map(
                cartao -> {
                    BigDecimal limiteBasico = cartao.getLimiteBasico();
                    BigDecimal idadeBD = new BigDecimal(dadosClienteDto.getIdade());

                    var fator = idadeBD.divide(BigDecimal.valueOf(10));
                    BigDecimal limiteAprovado = fator.multiply(limiteBasico);

                    return CartaoAprovadoDto.builder()
                            .cartao(cartao.getNome())
                            .bandeira(cartao.getBandeira())
                            .limiteAprovado(limiteAprovado)
                            .build();
                }
        ).toList();

        return RetornoAvaliacaoClienteOut.builder()
                .cartoes(listCartaoAprovado)
                .build();
    }

    public ProtocoloSolicitacaoCartao solicitacaoCartao(DadosSolicitacaoEmissaoCartao dados) {
       try {
           var protocolo = UUID.randomUUID().toString();
           return ProtocoloSolicitacaoCartao.builder()
                   .protocolo(protocolo)
                   .build();
       } catch (Exception e) {
           throw new ErroSolicitarCartaoException(e.getMessage());
       }
    }
}
