create table cliente (
    id_cliente integer not null,
    limite integer not null,
    saldo integer not null,
    primary key (id_cliente)
);

create table transacao (
    id_transacao integer not null,
    descricao varchar(255) not null,
    realizada_em timestamp(6) not null,
    tipo varchar not null,
    valor integer not null,
    cliente_id_cliente integer,
    primary key (id_transacao)
);

INSERT INTO cliente (id, limite, saldo) VALUES
('1', '100000', '0'),
('2', '80000', '0'),
('3', '1000000', '0'),
('4', '10000000', '0'),
('5', '500000', '0');