--create table if not exists cliente (
--    id integer not null,
--    limite integer not null,
--    saldo integer not null,
--    primary key (id)
--);
--
--create table if not exists transacao (
--    id integer not null,
--    descricao varchar(255) not null,
--    realizada_em timestamp(6) not null,
--    tipo varchar not null,
--    valor integer not null,
--    primary key (id)
--);

INSERT INTO cliente (id, limite, saldo) VALUES
('1', '100000', '0'),
('2', '80000', '0'),
('3', '1000000', '0'),
('4', '10000000', '0'),
('5', '500000', '0');