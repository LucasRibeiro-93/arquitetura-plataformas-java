package br.edu.infnet.lucasribeiroapi.controller;

import br.edu.infnet.lucasribeiroapi.exception.ResourceNotFoundException;
import br.edu.infnet.lucasribeiroapi.model.domain.Departamento;
import br.edu.infnet.lucasribeiroapi.model.repository.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @GetMapping
    public ResponseEntity<List<Departamento>> listar() {
        return ResponseEntity.ok(departamentoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> obterPorId(@PathVariable Integer id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento not found with id " + id));
        return ResponseEntity.ok(departamento);
    }

    @PostMapping
    public ResponseEntity<Departamento> criar(@RequestBody Departamento departamento) {
        Departamento novoDepartamento = departamentoRepository.save(departamento);
        return new ResponseEntity<>(novoDepartamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> atualizar(@PathVariable Integer id, @RequestBody Departamento departamentoAtualizado) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento not found with id " + id));
        departamento.setNome(departamentoAtualizado.getNome());
        departamento.setDescricao(departamentoAtualizado.getDescricao());
        departamento.setAtivo(departamentoAtualizado.getAtivo());
        return ResponseEntity.ok(departamentoRepository.save(departamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Departamento departamento = departamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Departamento not found with id " + id));
        departamentoRepository.delete(departamento);
        return ResponseEntity.noContent().build();
    }
}