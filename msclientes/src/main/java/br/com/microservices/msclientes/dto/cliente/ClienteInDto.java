package br.com.microservices.msclientes.dto.cliente;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.br.CPF;

@Data
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class ClienteInDto {

    @NotNull(message = "CPF é obrigatório")
    @Size(min = 11, max = 11, message = "CPF é inválido")
    @CPF(message = "CPF Inválido!")
    private String cpf;

    @NotNull(message = "CPF é obrigatório")
    private String nome;

    @NotNull(message = "Idade é obrigatório")
    private Integer idade;
}
