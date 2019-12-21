package com.rabelo.delivery.api.controller.teste;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.delivery.domain.model.Restaurante;
import com.rabelo.delivery.domain.repository.CozinhaRepository;
import com.rabelo.delivery.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/testes")
public class TesteController {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping
	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	@GetMapping("/restaurante/por-taxa-frete")
	public List<Restaurante> buscarPeriodoTaxaFrete(@RequestParam BigDecimal taxaInicio, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicio, taxaFinal);
	}

	@GetMapping("/restaurante/por-nome")
	public List<Restaurante> buscarPeloNome(@RequestParam String nome) {
		return restauranteRepository.findByNomeContaining(nome);
	}

	@GetMapping("/cozinhas/primeiro-nome")
	public Optional<Restaurante> oneNome(@RequestParam String nome) {

		return restauranteRepository.findFirstByNomeContaining(nome);

	}

	@GetMapping("/cozinhas/dois-nome")
	public List<Restaurante> doisNomes(@RequestParam String nome) {

		return restauranteRepository.findTop2ByNomeContaining(nome);

	}

	@GetMapping("/cozinhas/existe-nome")
	public boolean existeNomes(@RequestParam String nome) {

		return restauranteRepository.existsByNome(nome);

	}

	@GetMapping("/cozinhas/count-cozinha")
	public int qtdCozinha(@RequestParam Long cozinha) {

		return restauranteRepository.countByCozinhaId(cozinha);

	}

	@GetMapping("/cozinhas/consulta-queryJpql")
	public List<Restaurante> consultarQueryJpql(@RequestParam String nome, Long cozinha) {

		return restauranteRepository.consultarPorNome(nome, cozinha);

	}

	@GetMapping("/restaurante/por-nome-frete-queryJpql")
	public List<Restaurante> nomeFreteQueryJpql(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

		return restauranteRepository.find(nome, taxaInicial, taxaFinal);

	}

	@GetMapping("/restaurante/com-frete-gratis-specification")
	public List<Restaurante> restauranteFreteGratis(String nome) {
		return restauranteRepository.findComFreteGratis(nome);

	}

	@GetMapping("/restaurante/buscar_primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {

		return restauranteRepository.buscarPrimeiro();

	}

}
