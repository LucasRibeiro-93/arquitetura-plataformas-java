package br.edu.infnet.lucasribeiroapi.model.repository;

import br.edu.infnet.lucasribeiroapi.model.domain.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
}