package com.rabelo.delivery.domain.model;

public enum StatusPedido {

	CRIADO("Criado"),
	CONFIRMADO("Confirmado"),
	ENTREGUE("Entregue"),
	CANCELADO("Cancelado");

	private String nome;

	StatusPedido(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}
}
