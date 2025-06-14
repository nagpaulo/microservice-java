package br.com.microservices.mscartoes.service;
import br.com.microservices.mscartoes.domain.BandeiraCartao;
import br.com.microservices.mscartoes.dto.cartao.CartaoIn;
import br.com.microservices.mscartoes.dto.cartao.CartaoOut;
import br.com.microservices.mscartoes.entity.Cartao;
import br.com.microservices.mscartoes.repository.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {
    private final CartaoRepository repository;

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
}
