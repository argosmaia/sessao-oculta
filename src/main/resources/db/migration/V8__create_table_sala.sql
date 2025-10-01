-- ===============================
-- Ativar extensão uuidv7
-- ===============================
CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

-- ===============================
-- Tabela salas
-- ===============================
CREATE TABLE salas (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    nome text NOT NULL,
    logradouro text,
    bairro text,
    cep text,
    numero text,
    complemento text,
    cidade text,
    uf text
);
