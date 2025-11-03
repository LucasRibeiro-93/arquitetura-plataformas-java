package br.edu.infnet.lucasribeiroapi.controller;

import br.edu.infnet.lucasribeiroapi.exception.ResourceNotFoundException;
import br.edu.infnet.lucasribeiroapi.model.domain.InstituicaoFinanceira;
import br.edu.infnet.lucasribeiroapi.model.repository.InstituicaoFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoFinanceiraController {

    @Autowired
    private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    @GetMapping
    public ResponseEntity<List<InstituicaoFinanceira>> listar() {
        return ResponseEntity.ok(instituicaoFinanceiraRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstituicaoFinanceira> obterPorId(@PathVariable Integer id) {
        InstituicaoFinanceira instituicao = instituicaoFinanceiraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InstituicaoFinanceira not found with id " + id));
        return ResponseEntity.ok(instituicao);
    }

    @PostMapping
    public ResponseEntity<InstituicaoFinanceira> criar(@RequestBody InstituicaoFinanceira instituicao) {
        InstituicaoFinanceira novaInstituicao = instituicaoFinanceiraRepository.save(instituicao);
        return new ResponseEntity<>(novaInstituicao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstituicaoFinanceira> atualizar(@PathVariable Integer id, @RequestBody InstituicaoFinanceira instituicaoAtualizada) {
        InstituicaoFinanceira instituicao = instituicaoFinanceiraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InstituicaoFinanceira not found with id " + id));
        instituicao.setNome(instituicaoAtualizada.getNome());
        instituicao.setCnpj(instituicaoAtualizada.getCnpj());
        instituicao.setCodigoBanco(instituicaoAtualizada.getCodigoBanco());
        instituicao.setAtivo(instituicaoAtualizada.getAtivo());
        return ResponseEntity.ok(instituicaoFinanceiraRepository.save(instituicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        InstituicaoFinanceira instituicao = instituicaoFinanceiraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("InstituicaoFinanceira not found with id " + id));
        instituicaoFinanceiraRepository.delete(instituicao);
        return ResponseEntity.noContent().build();
    }
}