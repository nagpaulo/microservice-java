package br.com.microservices.mscartoes.entity;

import br.com.microservices.mscartoes.domain.BandeiraCartao;
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
@Table(name = "tb_cartoes", schema = "microservice")
public class Cartao {

    @Id
    @Column(name = "ci_cartoes")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nm_cartoes", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "nm_bandeira", nullable = false)
    private BandeiraCartao bandeira;

    @Column(name = "vl_renda", nullable = false)
    private BigDecimal renda;

    @Column(name = "vl_limite_basico", nullable = false)
    private BigDecimal limiteBasico;

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

    public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.renda = renda;
        this.limiteBasico = limiteBasico;
    }
}
