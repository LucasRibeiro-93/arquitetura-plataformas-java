package br.edu.infnet.lucasribeiroapi.controller;

import br.edu.infnet.lucasribeiroapi.model.domain.Conta;
import br.edu.infnet.lucasribeiroapi.model.loader.ContaLoader;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private List<Conta> contas = new ArrayList<>();

    public ContaController() {
        // Carrega os dados do arquivo XML para a memória
        this.contas = ContaLoader.loadFromXml("src/main/resources/repository/Conta.xml");
    }

    @GetMapping
    public List<Conta> listar() {
        return contas;
    }

    @GetMapping("/{id}")
    public Conta obterPorId(@PathVariable Integer id) {
        return contas.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @PostMapping
    public Conta criar(@RequestBody Conta conta) {
        contas.add(conta);
        return conta;
    }

    @PutMapping("/{id}")
    public Conta atualizar(@PathVariable Integer id, @RequestBody Conta contaAtualizada) {
        Conta conta = contas.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
        if (conta != null) {
            conta.setNumero(contaAtualizada.getNumero());
            conta.setTipo(contaAtualizada.getTipo());
            conta.setAtivo(contaAtualizada.getAtivo());
        }
        return conta;
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        contas.removeIf(c -> c.getId().equals(id));
    }
}