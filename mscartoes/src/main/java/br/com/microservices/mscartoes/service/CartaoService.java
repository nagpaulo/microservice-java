package br.com.microservices.mscartoes.service;
import br.com.microservices.mscartoes.domain.BandeiraCartao;
import br.com.microservices.mscartoes.dto.cartao.CartaoIn;
import br.com.microservices.mscartoes.dto.cartao.CartaoOut;
import br.com.microservices.mscartoes.dto.cartao.DadosSolicitacaoEmissaoCartao;
import br.com.microservices.mscartoes.entity.Cartao;
import br.com.microservices.mscartoes.entity.ClienteCartao;
import br.com.microservices.mscartoes.repository.CartaoRepository;
import br.com.microservices.mscartoes.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {
    private final CartaoRepository repository;
    private final ClienteCartaoRepository clienteCartaoRepository;


    @Transactional
    public CartaoOut save(CartaoIn cartaoIn) {
        var cartao = Cartao.builder()
                .nome(cartaoIn.getNome())
                .bandeira(BandeiraCartao.valueOf(cartaoIn.getBandeira().name()))
                .limiteBasico(cartaoIn.getLimiteBasico())
                .renda(cartaoIn.getRenda())
                .build();
        var cartaoEntity = repository.save(cartao);
        return CartaoOut.builder()
                .id(cartaoEntity.getId())
                .nome(cartaoEntity.getNome())
                .bandeira(cartaoEntity.getBandeira())
                .limiteBasico(cartaoEntity.getLimiteBasico())
                .renda(cartaoEntity.getRenda())
                .build();
    }

    public List<CartaoOut> getCartoesRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        var cartaoEntity = repository.findByRendaLessThanEqual(rendaBigDecimal);
        return cartaoEntity.stream().map(CartaoOut::fromEntity).toList();
    }

    public void receberSolicitar(DadosSolicitacaoEmissaoCartao dados) {
        Cartao cartao = repository.findById(dados.getIdCartao())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartao não encontrado"));

        ClienteCartao clienteCartao = ClienteCartao.builder()
                .cartao(cartao)
                .cpf(dados.getCpf())
                .limite(dados.getLimiteLiberado())
                .build();

        clienteCartaoRepository.save(clienteCartao);
    }
}
