-- ===============================
-- Ativar extensão uuidv7
-- ===============================
CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

-- ===============================
-- Tabela pedidos
-- ===============================
CREATE TABLE pedidos (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    ingresso_id uuid NOT NULL REFERENCES ingressos(id) ON DELETE CASCADE,
    valor numeric(10,2) NOT NULL,
    metodo_pagamento text NOT NULL CHECK (
        metodo_pagamento IN ('CREDITO','DEBITO','PIX','DINHEIRO')
    )
);

