package br.com.microservices.msclientes.dto.cliente;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ClienteOutDto {
    private String cpf;
    private String nome;
    private Integer idade;
}
