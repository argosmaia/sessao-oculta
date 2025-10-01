CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

-- ===============================
-- Tabela itens (associando Pedido e Produto)
-- ===============================
CREATE TABLE itens (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    produto_id uuid NOT NULL REFERENCES produtos(id) ON DELETE CASCADE,
    pedido_id  uuid NOT NULL REFERENCES pedidos(id) ON DELETE CASCADE,
    quantidade int NOT NULL
);
