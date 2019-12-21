package com.rabelo.delivery.jpa;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.rabelo.delivery.ApiDeliveryApplication;
import com.rabelo.delivery.domain.model.Restaurante;
import com.rabelo.delivery.domain.repository.RestauranteRepository;

public class CrudRestauranteMain {
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(ApiDeliveryApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantesRepository = applicationContext.getBean(RestauranteRepository.class);
		
		//TODOS OS RESTAURANTES
		List<Restaurante> todosRestaurantes = restaurantesRepository.findAll();
		
		System.out.println("----TODOS OS RESTAURANTES----");
		for(Restaurante restaurante : todosRestaurantes) {
			//System.out.println("RESTAURANTES: " + restaurante.getNome());
			System.out.printf("Nome do Restaurante: %s - Taxa de entrega: %f - Cozinha: %s\n",			
					restaurante.getNome(),
					restaurante.getTaxaFrete(),
					restaurante.getCozinha().getNome());
		
		}
		
		//BUSCAR POR ID RESTAURANTES
	Optional<Restaurante> restauranteId = restaurantesRepository.findById(2L);
	
	System.out.println("----BUSCA POR RESTAURANTE----");
	System.out.println("POR ID: " + restauranteId.get().getNome());
	
	//ADICIONAR POR RESTAURANTES
	Restaurante addRestaurante = new Restaurante();
	addRestaurante.setNome("Sabor Carioca");
	addRestaurante.setTaxaFrete(new BigDecimal(15.29));
	
	restaurantesRepository.save(addRestaurante);
	
	//ATUALIZAR POR  ID RESTAURANTES
	Restaurante putRestaurante = new Restaurante();
	putRestaurante.setId(3L);
	putRestaurante.setNome("Pizza Paulista");
	putRestaurante.setTaxaFrete(new BigDecimal(8.88));
	
	restaurantesRepository.save(putRestaurante);
	
	//REMOVER RESTAURANTE
	Restaurante excluirRestaurante = new Restaurante();
	excluirRestaurante.setId(1L);
	restaurantesRepository.delete(excluirRestaurante);
	
		
	}

}
