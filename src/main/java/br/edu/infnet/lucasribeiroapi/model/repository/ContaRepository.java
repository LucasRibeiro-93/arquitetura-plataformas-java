package br.edu.infnet.lucasribeiroapi.model.repository;

import br.edu.infnet.lucasribeiroapi.model.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
}