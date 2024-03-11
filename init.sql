CREATE UNLOGGED TABLE cliente (
    id int primary key,
    limite integer not null,
    saldo integer not null
);

CREATE UNLOGGED TABLE transacao (
    id SERIAL primary key,
    descricao VARCHAR(255) NOT NULL,
    realizada_em TIMESTAMP(6) NOT NULL,
    tipo VARCHAR NOT NULL,
    valor INTEGER NOT NULL,
    cliente_id INTEGER NOT NULL
);

CREATE INDEX idx_transacao_id_cliente ON transacao (cliente_id);
CREATE INDEX idx_transacao_id_cliente_realizada_em ON transacao (cliente_id, realizada_em DESC);

INSERT INTO cliente (id, limite, saldo) VALUES
('1', '100000', '0'),
('2', '80000', '0'),
('3', '1000000', '0'),
('4', '10000000', '0'),
('5', '500000', '0');