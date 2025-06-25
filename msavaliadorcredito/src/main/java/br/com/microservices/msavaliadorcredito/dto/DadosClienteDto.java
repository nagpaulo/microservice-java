package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DadosClienteDto {
    private Long id;
    private String nome;
}
