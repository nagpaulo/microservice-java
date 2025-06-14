package br.com.microservices.mscartoes.dto.cartao;

import br.com.microservices.mscartoes.domain.BandeiraCartao;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class CartaoIn {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
}
