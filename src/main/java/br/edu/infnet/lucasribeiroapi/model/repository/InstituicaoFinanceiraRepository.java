package br.edu.infnet.lucasribeiroapi.model.repository;

import br.edu.infnet.lucasribeiroapi.model.domain.InstituicaoFinanceira;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituicaoFinanceiraRepository extends JpaRepository<InstituicaoFinanceira, Integer> {
}