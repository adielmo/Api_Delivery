CREATE TABLE estado(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
nome VARCHAR(60) NOT NULL


)ENGINE=INNODB DEFAULT CHARSET=utf8;

ALTER TABLE cidade ADD CONSTRAINT FK_cidade_estado
FOREIGN KEY(estado_id) REFERENCES estado(id);