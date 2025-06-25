package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class CartaoClienteDto {
    private String nome;
    private String bandeira;
    private BigDecimal limiteLiberado;
}
