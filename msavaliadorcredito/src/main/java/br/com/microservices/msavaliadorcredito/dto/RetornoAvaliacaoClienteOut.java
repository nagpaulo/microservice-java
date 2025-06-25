package br.com.microservices.msavaliadorcredito.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class RetornoAvaliacaoClienteOut {
    private List<CartaoAprovadoDto> cartoes;
}
