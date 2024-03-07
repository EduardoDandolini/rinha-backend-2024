create table if not exists cliente (
    id integer not null,
    limite integer not null,
    saldo integer not null,
    primary key (id)
);

create table if not exists transacao (
    id INTEGER NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    realizada_em TIMESTAMP(6) NOT NULL,
    tipo VARCHAR NOT NULL,
    valor INTEGER NOT NULL,
    cliente_id INTEGER NOT NULL, -- Chave estrangeira
    PRIMARY KEY (id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id) -- Definição da chave estrangeira
);

INSERT INTO cliente (id, limite, saldo) VALUES
('1', '100000', '0'),
('2', '80000', '0'),
('3', '1000000', '0'),
('4', '10000000', '0'),
('5', '500000', '0');