package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CartaoOut {
    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal limiteBasico;
}
