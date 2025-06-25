package br.com.microservices.msavaliadorcredito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SituacaoClienteDto {
    private DadosClienteDto cliente;
    private List<CartaoClienteDto> cartoes;
}
