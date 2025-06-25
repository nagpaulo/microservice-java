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
public class CartaoDto {

    private Long id;
    private String nome;
    private String bandeira;
    private BigDecimal renda;
    private BigDecimal limiteBasico;
}
