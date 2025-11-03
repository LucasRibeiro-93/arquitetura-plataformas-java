package br.edu.infnet.lucasribeiroapi.controller;

import br.edu.infnet.lucasribeiroapi.model.domain.InstituicaoFinanceira;
import br.edu.infnet.lucasribeiroapi.model.loader.InstituicaoFinanceiraLoader;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoFinanceiraController {

    private List<InstituicaoFinanceira> instituicoes = new ArrayList<>();

    public InstituicaoFinanceiraController() {
        // Carrega os dados do arquivo XML para a memória
        this.instituicoes = InstituicaoFinanceiraLoader.loadFromXml("src/main/resources/repository/InstituicaoFinanceira.xml");
    }

    @GetMapping
    public List<InstituicaoFinanceira> listar() {
        return instituicoes;
    }

    @GetMapping("/{id}")
    public InstituicaoFinanceira obterPorId(@PathVariable Integer id) {
        return instituicoes.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public InstituicaoFinanceira criar(@RequestBody InstituicaoFinanceira instituicao) {
        instituicoes.add(instituicao);
        return instituicao;
    }

    @PutMapping("/{id}")
    public InstituicaoFinanceira atualizar(@PathVariable Integer id, @RequestBody InstituicaoFinanceira instituicaoAtualizada) {
        InstituicaoFinanceira instituicao = instituicoes.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
        if (instituicao != null) {
            instituicao.setNome(instituicaoAtualizada.getNome());
            instituicao.setCnpj(instituicaoAtualizada.getCnpj());
            instituicao.setCodigoBanco(instituicaoAtualizada.getCodigoBanco());
            instituicao.setAtivo(instituicaoAtualizada.getAtivo());
        }
        return instituicao;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        instituicoes.removeIf(i -> i.getId().equals(id));
    }
}