INSERT INTO TB_UNIDADE(ID, NOME, ENDERECO) VALUES
(1,'Cristovão', 'Rua Cristovão Colombo');

INSERT INTO TB_UNIDADE(ID, NOME, ENDERECO) VALUES
(2,'Centro', 'Rua Alberto Bins');

INSERT INTO TB_CLIENTE (ID, NOME_CLIENTE, CPF_CLIENTE, INADIMPLENTE, ID_UNIDADE) VALUES
(1, 'Ciclano Santos', '111.222.333-44', TRUE, 1);

INSERT INTO TB_CLIENTE (ID, NOME_CLIENTE, CPF_CLIENTE, INADIMPLENTE, ID_UNIDADE) VALUES
(2, 'Fulano Santos', '111.222.333-44', TRUE, 2);


INSERT INTO TB_ANIMAL (id, client_id, data_nascimento, especie, nome, ID_UNIDADE)
VALUES (1, 1, '2019-01-02', 'MAMIFERO', 'Pluto', 1);

INSERT INTO TB_ANIMAL (id, client_id, data_nascimento, especie, nome, ID_UNIDADE)
VALUES (2, 2, '2019-01-02', 'MAMIFERO', 'Pluto', 2);

INSERT INTO TB_ANIMAL (id, client_id, data_nascimento, especie, nome, ID_UNIDADE)
VALUES (3, 2, '2019-01-02', 'MAMIFERO', 'Rex', 2);

