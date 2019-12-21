package com.rabelo.delivery.domain.infrastructure.repository.pagamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.rabelo.delivery.domain.model.FormaPagamento;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public FormaPagamento findByNome(String nome) {
	
return manager.createQuery("from FormaPagamento where nome = :nome", FormaPagamento.class)
				.setParameter("nome", nome).getSingleResult();
	}

}
