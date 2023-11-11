create table pessoa(
    id serial primary key,
    nome varchar(200),
    ativo BOOLEAN,
    logradouro varchar,
    numero varchar,
    complemento varchar,
    bairro varchar,
    cep varchar,
    cidade varchar,
    estado varchar
);

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado)
VALUES
    ('João Silva', true, 'Rua A', '123', 'Apto 1', 'Centro', '12345-678', 'Cidade A', 'Estado A'),
    ('Maria Souza', true, 'Avenida B', '456', 'Casa 2', 'Bairro X', '98765-432', 'Cidade B', 'Estado B'),
    ('Carlos Ferreira', true, 'Rua C', '789', 'Sala 3', 'Bairro Y', '54321-876', 'Cidade C', 'Estado C'),
    ('Ana Oliveira', true, 'Avenida D', '1011', 'Bloco 4', 'Centro', '13579-642', 'Cidade D', 'Estado D'),
    ('Pedro Santos', false, 'Rua E', '1315', 'Casa 5', 'Bairro Z', '24680-357', 'Cidade E', 'Estado E');

