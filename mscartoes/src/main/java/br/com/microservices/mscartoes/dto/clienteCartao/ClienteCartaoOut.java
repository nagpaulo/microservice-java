package br.com.microservices.mscartoes.dto.clienteCartao;

import br.com.microservices.mscartoes.dto.cartao.CartaoOut;
import br.com.microservices.mscartoes.entity.Cartao;
import br.com.microservices.mscartoes.entity.ClienteCartao;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ClienteCartaoOut {
    private String cpf;
    private Cartao cartao;
    private BigDecimal limite;

    public static ClienteCartaoOut fromEntity(ClienteCartao clienteCartao) {
        return ClienteCartaoOut.builder()
                .cartao(clienteCartao.getCartao())
                .cpf(clienteCartao.getCpf())
                .limite(clienteCartao.getLimite())
                .build();
    }
}
