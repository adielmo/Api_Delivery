package com.rabelo.delivery.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabelo.delivery.domain.exception.EntidadeEmUsoException;
import com.rabelo.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.rabelo.delivery.domain.model.Estado;
import com.rabelo.delivery.domain.repository.EstadoRepository;
import com.rabelo.delivery.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id){
		Optional<Estado> estadoId = estadoRepository.findById(id);
		
		return estadoId.isPresent() ? ResponseEntity.ok(estadoId.get()) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Estado> salvar(@RequestBody Estado estado){
	Estado estadoSalvo = estadoService.salvar(estado);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoSalvo);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado){
		Optional<Estado> estadoAtual = estadoRepository.findById(id);
		
		if (estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			Estado estadoSalvo = estadoService.salvar(estadoAtual.get());
			
			return ResponseEntity.ok(estadoSalvo);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long id){
		try {
		 estadoService.excluir(id);
		 return ResponseEntity.noContent().build();
		 
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		}catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	

}
