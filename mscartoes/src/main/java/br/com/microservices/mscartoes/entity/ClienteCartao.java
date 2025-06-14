package br.com.microservices.mscartoes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "tb_cliente_cartao", schema = "microservice")
public class ClienteCartao {

    @Id
    @Column(name = "ci_cliente_cartao")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_cpf", nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(name = "cd_cartao", nullable = false)
    private Cartao cartao;

    @Column(name = "vl_limite", nullable = false)
    private BigDecimal limite;

    @Column(name = "dt_cadastro", updatable = false)
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataCadastro;

    @Column(name = "dt_modificacao")
    @JsonFormat(pattern="dd/MM/yyyy")
    private LocalDate dataModificacao;

    @PrePersist
    public void prePersist() {
        setDataCadastro(LocalDate.now());
    }

    @PreUpdate
    public void preUpdate() {
        setDataModificacao(LocalDate.now());
    }
}
