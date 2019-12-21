package com.rabelo.delivery.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rabelo.delivery.domain.infrastructure.repository.restaurante.RestauranteRepositoryQuereis;
import com.rabelo.delivery.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>
      ,RestauranteRepositoryQuereis, JpaSpecificationExecutor<Restaurante>{
	
     @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
	List<Restaurante> findAll();
     
     List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicio, BigDecimal taxaFinal);
     
     List<Restaurante> findByNomeContaining(String nome);

 	Optional<Restaurante> findFirstByNomeContaining(String nome);

 	List<Restaurante> findTop2ByNomeContaining(String nome);

 	boolean existsByNome(String nome);

 	int countByCozinhaId(Long cozinha);

 	 @Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
 	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
     
     
     
	


}
