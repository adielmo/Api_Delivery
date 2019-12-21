package com.rabelo.delivery.domain.infrastructure.repository.restaurante;

import java.math.BigDecimal;
import java.util.List;

import com.rabelo.delivery.domain.model.Restaurante;

public interface RestauranteRepositoryQuereis {
	
	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);

}
