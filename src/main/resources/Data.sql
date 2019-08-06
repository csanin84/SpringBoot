INSERT INTO TB_UNIDADE(NOME, ENDERECO) VALUES
('Unidade Cristovão Colombo', 'Rua Cristovão Colombo, 100');

INSERT INTO TB_CLIENTE (NOME_CLIENTE, CPF_CLIENTE, INADIMPLENTE, ID_UNIDADE) VALUES
('Fulano da Silva', '000.111.222-33', FALSE, 1);
INSERT INTO TB_CLIENTE (NOME_CLIENTE, CPF_CLIENTE, INADIMPLENTE, ID_UNIDADE) VALUES
('Ciclano Santos', '111.222.333-44', TRUE, 1);

INSERT INTO TB_ANIMAL (client_id, data_nascimento, especie, nome, ID_UNIDADE)
VALUES (1, '2019-01-02', 'MAMIFERO', 'Totó', 1);

INSERT INTO TB_ANIMAL (client_id, data_nascimento, especie, nome, ID_UNIDADE)
VALUES (2, '2016-07-31', 'REPTIL', 'Rex', 1);


INSERT INTO TB_PRODUTO
(ID, VALOR, DESCRICAO, ID_ANIMAL)
VALUES (1, 130.00, 'Vacina aftosa', 1);

INSERT INTO TB_PRODUTO
(ID, VALOR, DESCRICAO, ID_ANIMAL)
VALUES (2, 40.00, 'Ração', 2);