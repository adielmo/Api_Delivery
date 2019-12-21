package com.rabelo.delivery.domain.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.rabelo.delivery.domain.model.Restaurante;

public class RestauranteSpacs {
	
	public static Specification<Restaurante> comFreteGratis(){
		
 return (root, query, builder)->
 builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome){
		return (root, query, builder) ->
		builder.like(root.get("nome"), "%" + nome + "%");
	}

}
