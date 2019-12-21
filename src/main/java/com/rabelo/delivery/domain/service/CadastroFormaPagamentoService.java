package com.rabelo.delivery.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rabelo.delivery.domain.exception.EntidadeEmUsoException;
import com.rabelo.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.rabelo.delivery.domain.infrastructure.repository.pagamento.FormaPagamentoRepositoryQuery;
import com.rabelo.delivery.domain.model.FormaPagamento;
import com.rabelo.delivery.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository pagamentoRepository;

	@Autowired
	private FormaPagamentoRepositoryQuery pagamentoQuery;

	public FormaPagamento salvar(FormaPagamento formaPagamento) {

		return pagamentoRepository.save(formaPagamento);
	}

	public void excluir(Long id) {
		try {
			pagamentoRepository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de cidade com código %d", id));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Código de Forma Pagamento %d não pode ser removida, pois está em uso", id));
		}
	}

}
