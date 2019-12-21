package com.rabelo.delivery.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabelo.delivery.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository  extends JpaRepository<FormaPagamento, Long>{
	

	

}
