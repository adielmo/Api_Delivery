package com.rabelo.delivery.domain.infrastructure.repository.pagamento;

import com.rabelo.delivery.domain.model.FormaPagamento;

public interface FormaPagamentoRepositoryQuery {
	
	FormaPagamento findByNome(String nome);

}
