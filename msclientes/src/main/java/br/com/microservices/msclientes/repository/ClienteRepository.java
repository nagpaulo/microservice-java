package br.com.microservices.msclientes.repository;

import br.com.microservices.msclientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
}
