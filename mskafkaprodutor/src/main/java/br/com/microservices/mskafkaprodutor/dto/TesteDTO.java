package br.com.microservices.mskafkaprodutor.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
public class TesteDTO {
    private Integer numero;
    private String descricao;
    private BigDecimal valor;
}
