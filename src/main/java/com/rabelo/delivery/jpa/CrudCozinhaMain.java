package com.rabelo.delivery.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.rabelo.delivery.ApiDeliveryApplication;
import com.rabelo.delivery.domain.model.Cozinha;
import com.rabelo.delivery.domain.repository.CozinhaRepository;

public class CrudCozinhaMain {

	public static void main(String[] args) {
	 ApplicationContext applicationContext = new SpringApplicationBuilder(ApiDeliveryApplication.class)
			   .web(WebApplicationType.NONE)
			   .run(args);
	 CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
	 
	 //BUSCAR TODAS AS COZINHAS
	  List<Cozinha> todasCozinhas = cozinhas.findAll();
	  System.out.println("----BUSCAR TODAS AS COZINHAS----------------");
	  for(Cozinha cozinha : todasCozinhas) {
		  System.out.println("COZINHA: " + cozinha.getNome());
	  }
	  
	  //BUSCAR POR ID UMA COZINHA
	
	  Optional<Cozinha> porIdCozinha =cozinhas.findById(2L);
	  System.out.println("----BUSCAR POR ID UMA COZINHA----------------");
	  System.out.println("BUSCAR POR ID: " + porIdCozinha.get().getNome());
	 
	//ADICIONAR UMA COZINHA
	  Cozinha addCozinha = new Cozinha();
	  addCozinha.setNome("Maranhense");
	  
	  cozinhas.save(addCozinha);
	  
	//ATUALIZAR UMA COZINHA
	  Cozinha putCozinha = new Cozinha();
	  putCozinha.setId(2L);
	  putCozinha.setNome("Americana");
	  cozinhas.save(putCozinha);
	  
	//REMOVER UMA COZINHA
	  Cozinha excluirCozinha = new Cozinha();
	  excluirCozinha.setId(3L);
	  cozinhas.deleteById(excluirCozinha.getId());
	  
	 

	}

}
