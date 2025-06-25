package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ClienteDto {
    private Long id;
    private String cpf;
    private String nome;
    private Integer idade;
}
