package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class DadosAvaliacaoDto {
    private String cpf;
    private Long renda;
}
