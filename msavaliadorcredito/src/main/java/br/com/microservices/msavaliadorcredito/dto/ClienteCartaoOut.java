package br.com.microservices.msavaliadorcredito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ClienteCartaoOut {
    private String nome;
    private CartaoDto cartao;
    private BigDecimal limite;
}
