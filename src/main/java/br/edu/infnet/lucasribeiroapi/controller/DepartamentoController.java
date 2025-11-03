package br.edu.infnet.lucasribeiroapi.controller;

import br.edu.infnet.lucasribeiroapi.model.domain.Departamento;
import br.edu.infnet.lucasribeiroapi.model.loader.DepartamentoLoader;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private List<Departamento> departamentos = new ArrayList<>();

    public DepartamentoController() {
        // Carrega os dados do arquivo XML para a memória
        this.departamentos = DepartamentoLoader.loadFromXml("src/main/resources/repository/Departamento.xml");
    }

    @GetMapping
    public List<Departamento> listar() {
        return departamentos;
    }

    @GetMapping("/{id}")
    public Departamento obterPorId(@PathVariable Integer id) {
        return departamentos.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Departamento criar(@RequestBody Departamento departamento) {
        departamentos.add(departamento);
        return departamento;
    }

    @PutMapping("/{id}")
    public Departamento atualizar(@PathVariable Integer id, @RequestBody Departamento departamentoAtualizado) {
        Departamento departamento = departamentos.stream().filter(d -> d.getId().equals(id)).findFirst().orElse(null);
        if (departamento != null) {
            departamento.setNome(departamentoAtualizado.getNome());
            departamento.setDescricao(departamentoAtualizado.getDescricao());
            departamento.setAtivo(departamentoAtualizado.getAtivo());
        }
        return departamento;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        departamentos.removeIf(d -> d.getId().equals(id));
    }
}