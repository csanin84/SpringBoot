CREATE TABLE TB_UNIDADE (
ID bigint generated by default as identity,
NOME VARCHAR2(100),
ENDERECO VARCHAR2(300),
primary key (ID)
);

create table tb_animal(
id bigint generated by default as identity,
client_id bigint,
data_nascimento date,
especie varchar2(10),
nome varchar2 (100),
ID_UNIDADE bigint,
primary key (id),
CONSTRAINT FK_ANIMAL_UNIDADE FOREIGN KEY (ID_UNIDADE) REFERENCES TB_UNIDADE(ID)
);

CREATE TABLE TB_CLIENTE (
ID bigint generated by default as identity,
NOME_CLIENTE VARCHAR2(100),
CPF_CLIENTE VARCHAR2(14),
INADIMPLENTE BOOLEAN,
ID_UNIDADE bigint,
primary key (ID),
CONSTRAINT FK_CLIENTE_UNIDADE  FOREIGN KEY (ID_UNIDADE) REFERENCES TB_UNIDADE(ID)
);

CREATE TABLE TB_PRODUTO(
ID bigint generated by default as identity,
VALOR NUMBER(8,2),
DESCRICAO VARCHAR2(400),
ID_ANIMAL bigint,
primary key (ID),
CONSTRAINT FK_PRODUTO_ANIMAL  FOREIGN KEY (ID_ANIMAL) REFERENCES TB_ANIMAL(ID)
);




