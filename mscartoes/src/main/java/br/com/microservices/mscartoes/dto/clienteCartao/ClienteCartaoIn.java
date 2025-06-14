package br.com.microservices.mscartoes.dto.clienteCartao;

import br.com.microservices.mscartoes.domain.BandeiraCartao;
import br.com.microservices.mscartoes.entity.Cartao;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ClienteCartaoIn {
    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal limiteLiberado;
}
