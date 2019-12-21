CREATE TABLE pedido(
	id BIGINT NOT NULL AUTO_INCREMENT,
	taxa_frete DECIMAL(10,2) NOT NULL,
	sub_total DECIMAL(10, 2) NOT NULL,
	valor_total DECIMAL(10, 2) NOT NULL,

	data_criacao DATETIME NOT NULL,
	data_confirmacao DATETIME NULL,
	data_cancelamento DATETIME NULL,
	data_entrega DATETIME NULL,

	status_pedido VARCHAR(15) NOT NULL,

	endereco_cep VARCHAR(10) NOT NULL,
	endereco_complemento VARCHAR(100) NULL,
	endereco_logradouro VARCHAR(60) NOT NULL,
	endereco_numero VARCHAR(15) NOT NULL,
    endereco_bairro VARCHAR(60) NOT NULL,

	endereco_cidade_id BIGINT(20)NOT NULL,

	usuario_cliente_id BIGINT(20) NOT NULL,
	restaurante_id BIGINT(20) NOT NULL,
	forma_pagamento_id BIGINT(20) NOT NULL,

 primary key (id),

 constraint fk_pedido_usuario_cliente foreign key(usuario_cliente_id) references usuario(id),
 constraint fk_pedido_restaurante foreign key(restaurante_id) references restaurante(id),
 constraint fk_pedido_forma_pagamento foreign key(forma_pagamento_id) references forma_pagamento(id)

)ENGINE=INNODB DEFAULT CHARSET=utf8;



CREATE TABLE item_pedido(
	id BIGINT NOT NULL AUTO_INCREMENT,
	quantidade smallint(6) NOT NULL,
	preco_unitario DECIMAL(10, 2) NOT NULL,
	valor_total DECIMAL(10, 2) NOT NULL,
	observacao VARCHAR(100) NULL,

	pedido_id BIGINT(20) NOT NULL,
	produto_id BIGINT(20) NOT NULL,

	primary key(id),
	unique key uk_item_pedido_produto(pedido_id, produto_id),
	
	constraint fk_item_pedido_pedido foreign key(pedido_id) references pedido(id),
	constraint fk_item_pedido_produto foreign key(produto_id) references produto(id)

)ENGINE=INNODB DEFAULT CHARSET=utf8;
