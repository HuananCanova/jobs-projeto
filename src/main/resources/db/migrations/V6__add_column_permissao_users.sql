-- Migration para adicionar a coluna "permissoes" à tabela "aluno"
ALTER TABLE users
    ADD COLUMN permissoes VARCHAR(255); -- Você pode ajustar o tipo de dados e o tamanho do array conforme necessário
