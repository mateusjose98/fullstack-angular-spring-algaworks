create table usuario(
    id serial primary key,
    nome varchar,
    email varchar,
    senha varchar
);
create table permissao(
    id serial primary key,
    descricao varchar
);

create table usuario_permissao(
    permissao_id bigint,
    usuario_id bigint,
    primary key (permissao_id, usuario_id),
    CONSTRAINT fk_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    CONSTRAINT fk_permissao FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);

-- Inserir dados na tabela usuario com senhas em formato Bcrypt
INSERT INTO usuario (nome, email, senha) VALUES
    ('João Silva', 'joao@email.com', '$2a$12$yKErKJ/tlgxKlbZBtL.wT.5c4/H.pF6vf5uju2H2dRmuiv1fA4/jm'),
    ('Maria Oliveira', 'maria@email.com', '$2a$12$yKErKJ/tlgxKlbZBtL.wT.5c4/H.pF6vf5uju2H2dRmuiv1fA4/jm'),
    ('Carlos Santos', 'carlos@email.com', '$2a$12$yKErKJ/tlgxKlbZBtL.wT.5c4/H.pF6vf5uju2H2dRmuiv1fA4/jm');


-- Inserir dados na tabela permissao
INSERT INTO permissao (descricao) VALUES
    ('Leitura'),
    ('Escrita'),
    ('Administração');

-- Inserir dados na tabela usuario_permissao
INSERT INTO usuario_permissao (usuario_id, permissao_id) VALUES
    (1, 1), -- João Silva tem permissão de Leitura
    (2, 2), -- Maria Oliveira tem permissão de Escrita
    (3, 3), -- Carlos Santos tem permissão de Administração
    (1, 2), -- João Silva também tem permissão de Escrita
    (2, 1); -- Maria Oliveira também tem permissão de Leitura

