CREATE TABLE cliente(
	id_cliente BIGSERIAL PRIMARY KEY,
	cpf VARCHAR(11) UNIQUE NOT NULL,
	nome VARCHAR(255) NOT NULL,
	data_nascimento DATE,
	email VARCHAR(100) UNIQUE NOT NULL,
	senha_hash VARCHAR(255) NOT NULL,
	perfil VARCHAR(20) NOT NULL DEFAULT 'CLIENTE',
	data_cadastro TIMESTAMP DEFAULT NOW()
);

CREATE TABLE conta(
	id_conta BIGSERIAL PRIMARY KEY,
	id_cliente BIGINT  NOT NULL,
	saldo_disponivel NUMERIC(15,2) NOT NULL DEFAULT 0,
	data_abertura DATE DEFAULT CURRENT_DATE,
	ativo BOOLEAN DEFAULT true,

	CONSTRAINT fk_conta_cliente
		FOREIGN KEY(id_cliente)
		REFERENCES cliente(id_cliente)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
);

CREATE TABLE produto(
	id_produto BIGSERIAL PRIMARY KEY,
	nome_produto VARCHAR(100) UNIQUE NOT NULL,
	tipo_produto VARCHAR(50) NOT NULL,
	percentual_cdi NUMERIC(6,2) NOT NULL,
	carencia_dias INTEGER NOT NULL,
	prazo_dias INTEGER NOT NULL,
	taxa_fixa_anual NUMERIC(8,4),
    risco VARCHAR(20),
    valor_minimo_aplicacao NUMERIC(15,2) NOT NULL CHECK (valor_minimo_aplicacao >= 0),
    is_ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE investimento(
	id_investimento BIGSERIAL PRIMARY KEY,
	id_cliente BIGINT  NOT NULL,
	id_produto BIGINT  NOT NULL,
	data_contratacao TIMESTAMP DEFAULT NOW(),
	vlr_aplicado_inicial NUMERIC(15,2) NOT NULL,
	status VARCHAR(30) NOT NULL DEFAULT 'EM_CARENCIA',
	data_vencimento DATE,
	taxa_cdi NUMERIC(6,2),
	rendimento_acumulado NUMERIC(15,2) NOT NULL DEFAULT 0,

	CONSTRAINT fk_investimento_cliente
		FOREIGN KEY(id_cliente)
		REFERENCES cliente(id_cliente)
		ON DELETE RESTRICT
		ON UPDATE CASCADE,

	CONSTRAINT fk_investimento_produto
		FOREIGN KEY(id_produto)
		REFERENCES produto(id_produto)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
);

CREATE TABLE bloqueio_cliente(
	id_bloqueio BIGSERIAL PRIMARY KEY,
	id_cliente BIGINT  NOT NULL,
	id_investimento BIGINT  NOT NULL,
	data_inicio DATE NOT NULL,
	data_fim DATE,
	status VARCHAR(20) NOT NULL DEFAULT 'ATIVO',

	CONSTRAINT fk_bloqueio_cliente
		FOREIGN KEY(id_cliente)
		REFERENCES cliente(id_cliente)
		ON DELETE RESTRICT
		ON UPDATE CASCADE,

	CONSTRAINT fk_bloqueio_investimento
		FOREIGN KEY(id_investimento)
		REFERENCES investimento(id_investimento)
		ON DELETE RESTRICT
		ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS usuario(
    id_usuario BIGSERIAL PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    senha VARCHAR(250) NOT NULL
);

