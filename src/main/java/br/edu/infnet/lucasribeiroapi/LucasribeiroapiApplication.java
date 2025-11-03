package br.edu.infnet.lucasribeiroapi;

import br.edu.infnet.lucasribeiroapi.model.domain.Conta;
import br.edu.infnet.lucasribeiroapi.model.domain.Departamento;
import br.edu.infnet.lucasribeiroapi.model.domain.InstituicaoFinanceira;
import br.edu.infnet.lucasribeiroapi.model.loader.ContaLoader;
import br.edu.infnet.lucasribeiroapi.model.loader.DepartamentoLoader;
import br.edu.infnet.lucasribeiroapi.model.loader.InstituicaoFinanceiraLoader;
import br.edu.infnet.lucasribeiroapi.model.repository.ContaRepository;
import br.edu.infnet.lucasribeiroapi.model.repository.DepartamentoRepository;
import br.edu.infnet.lucasribeiroapi.model.repository.InstituicaoFinanceiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
public class LucasribeiroapiApplication {

	@Autowired
	private ContaRepository contaRepository;

	@Autowired
	private DepartamentoRepository departamentoRepository;

	@Autowired
	private InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

	public static void main(String[] args) {
		SpringApplication.run(LucasribeiroapiApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void init() {
		// Load and save InstituicoesFinanceiras
		List<InstituicaoFinanceira> instituicoes = InstituicaoFinanceiraLoader.loadFromXml("src/main/resources/repository/InstituicaoFinanceira.xml");
		instituicaoFinanceiraRepository.saveAll(instituicoes);

		// Load and save Departamentos
		List<Departamento> departamentos = DepartamentoLoader.loadFromXml("src/main/resources/repository/Departamento.xml");
		departamentoRepository.saveAll(departamentos);

		// Load and save Contas
		List<Conta> contas = ContaLoader.loadFromXml("src/main/resources/repository/Conta.xml");
		contaRepository.saveAll(contas);
	}
}