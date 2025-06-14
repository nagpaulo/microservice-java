package br.com.microservices.mscartoes.repository;

import br.com.microservices.mscartoes.entity.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
