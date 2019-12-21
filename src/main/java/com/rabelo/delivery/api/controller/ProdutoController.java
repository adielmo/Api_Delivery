package com.rabelo.delivery.api.controller;

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

import com.rabelo.delivery.domain.model.Produto;
import com.rabelo.delivery.domain.repository.ProdutoRepository;
import com.rabelo.delivery.domain.service.CadastroProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CadastroProdutoService produtoService;

	@GetMapping
	public List<Produto> listar() {
		return produtoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> buscarId(@PathVariable Long id) {
		Optional<Produto> produtoId = produtoRepository.findById(id);

		return produtoId.isPresent() ? ResponseEntity.ok(produtoId.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Produto produto) {
		Produto produtoSalvo = produtoService.salvar(produto);
		return ResponseEntity.ok().body(produtoSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
		Optional<Produto> produtoSalvo = produtoRepository.findById(id);

		if (produtoSalvo.isPresent()) {
			BeanUtils.copyProperties(produto, produtoSalvo.get(), "id");
			Produto produtoNovo = produtoService.salvar(produtoSalvo.get());
			return ResponseEntity.ok().body(produtoNovo);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluir(@PathVariable Long id) {
		Optional<Produto> produtoId = produtoRepository.findById(id);

		if (produtoId.isPresent()) {
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();

		}

		return ResponseEntity.notFound().build();
	}

}
