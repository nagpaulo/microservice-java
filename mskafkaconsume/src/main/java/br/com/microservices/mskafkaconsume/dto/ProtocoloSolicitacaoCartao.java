package br.com.microservices.mskafkaconsume.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloSolicitacaoCartao {
    private String protocolo;
}
