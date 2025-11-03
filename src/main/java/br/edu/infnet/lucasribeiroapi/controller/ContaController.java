package br.edu.infnet.lucasribeiroapi.controller;

import br.edu.infnet.lucasribeiroapi.exception.ResourceNotFoundException;
import br.edu.infnet.lucasribeiroapi.model.domain.Conta;
import br.edu.infnet.lucasribeiroapi.model.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping
    public ResponseEntity<List<Conta>> listar() {
        return ResponseEntity.ok(contaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> obterPorId(@PathVariable Integer id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta not found with id " + id));
        return ResponseEntity.ok(conta);
    }

    @PostMapping
    public ResponseEntity<Conta> criar(@RequestBody Conta conta) {
        Conta novaConta = contaRepository.save(conta);
        return new ResponseEntity<>(novaConta, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizar(@PathVariable Integer id, @RequestBody Conta contaAtualizada) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta not found with id " + id));
        conta.setNumero(contaAtualizada.getNumero());
        conta.setTipo(contaAtualizada.getTipo());
        conta.setAtivo(contaAtualizada.getAtivo());
        return ResponseEntity.ok(contaRepository.save(conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Conta conta = contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta not found with id " + id));
        contaRepository.delete(conta);
        return ResponseEntity.noContent().build();
    }
}