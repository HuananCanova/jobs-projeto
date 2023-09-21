-- Migration para criar a tabela Empresa

CREATE TABLE empresa (
                         id SERIAL PRIMARY KEY,
                         razao_social VARCHAR(255),
                         CNPJ VARCHAR(14) UNIQUE,
                         email VARCHAR(255)
);
