package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class CartaoAprovadoDto {
    private String cartao;
    private String bandeira;
    private BigDecimal limiteAprovado;
}
