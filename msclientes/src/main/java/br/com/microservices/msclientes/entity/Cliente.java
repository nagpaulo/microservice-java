package br.com.microservices.msclientes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "tb_clientes", schema = "microservice")
public class Cliente {

    @Id
    @Column(name = "ci_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nr_cpf", nullable = false)
    private String cpf;

    @Column(name = "nm_cliente", nullable = false)
    private String nome;

    @Column(name = "nr_idade", nullable = false)
    private Integer idade;

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

    public Cliente(String cpf, String nome, Integer idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
    }
}
