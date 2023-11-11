create table lancamento(
    id serial primary key,
    descricao TEXT,
    data_vencimento DATE,
    data_pagamento DATE,
    valor decimal(10, 2),
    obs varchar,
    tipo varchar,
    categoria_id bigint,
    pessoa_id bigint,
    CONSTRAINT fk_categoria_id FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    CONSTRAINT fk_pessoa_id FOREIGN KEY (pessoa_id) REFERENCES pessoa(id)
);



INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, obs, tipo, categoria_id, pessoa_id)
VALUES ('Compra de material de escritório', '2023-11-15', '2023-11-10', 150.00, 'Pagamento antecipado', 'Despesa', 1, 2);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, obs, tipo, categoria_id, pessoa_id)
VALUES ('Recebimento de cliente', '2023-11-20', NULL, 500.00, 'Pagamento pendente', 'Receita', 2, 1);

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, obs, tipo, categoria_id, pessoa_id)
VALUES ('Pagamento de aluguel', '2023-11-05', '2023-11-05', 1200.00, 'Aluguel do mês', 'Despesa', 3, 3);
