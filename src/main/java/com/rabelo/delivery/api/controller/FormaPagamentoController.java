package com.rabelo.delivery.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rabelo.delivery.domain.model.FormaPagamento;
import com.rabelo.delivery.domain.repository.FormaPagamentoRepository;
import com.rabelo.delivery.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService pagamentoService;
	
	@GetMapping
	public List<FormaPagamento> listar(){
		return formaPagamentoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Long id){
		Optional<FormaPagamento> pagamentoId = formaPagamentoRepository.findById(id);
		
		return pagamentoId.isPresent() ? ResponseEntity.ok(pagamentoId.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<FormaPagamento> adicionar(@RequestBody FormaPagamento formaPagamento){
	
		FormaPagamento pagamentoSalvo= pagamentoService.salvar(formaPagamento);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		  .buildAndExpand(pagamentoSalvo.getId()).toUri();
		
		return ResponseEntity.created(uri).body(pagamentoSalvo);
	
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<FormaPagamento> atuliza(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento){
		
			Optional<FormaPagamento> pagamentoId= formaPagamentoRepository.findById(id);
			
			if (pagamentoId.isPresent()) {
				
				BeanUtils.copyProperties(formaPagamento, pagamentoId.get(), "id");
				FormaPagamento pagamentoSalvo = pagamentoService.salvar(pagamentoId.get());
				
				return ResponseEntity.ok(pagamentoSalvo);
			}
			
		 return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<FormaPagamento> remover(@PathVariable Long id){
		Optional<FormaPagamento> pagamentoId = formaPagamentoRepository.findById(id);
		
		if (pagamentoId.isPresent()) {
			pagamentoService.excluir(pagamentoId.get().getId());
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
