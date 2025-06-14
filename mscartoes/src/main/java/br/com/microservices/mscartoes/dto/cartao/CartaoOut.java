package br.com.microservices.mscartoes.dto.cartao;

import br.com.microservices.mscartoes.domain.BandeiraCartao;
import br.com.microservices.mscartoes.entity.Cartao;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CartaoOut {
    private Long id;
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;

    public static CartaoOut fromEntity(Cartao cartao) {
        return CartaoOut.builder()
                .id(cartao.getId())
                .nome(cartao.getNome())
                .bandeira(cartao.getBandeira())
                .renda(cartao.getRenda())
                .limiteBasico(cartao.getLimiteBasico())
                .build();
    }
}
