CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

CREATE TABLE ingressos (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    usuario_id uuid NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    filme_id   uuid NOT NULL REFERENCES filmes(id) ON DELETE CASCADE,
    sessao_id  uuid NOT NULL REFERENCES sessoes(id) ON DELETE CASCADE,
    sala_id    uuid NOT NULL REFERENCES salas(id) ON DELETE CASCADE,
    hash_validacao_ingresso text NOT NULL UNIQUE,
    qr_code_hash_validacao_ingresso text,
    criado_em timestamp NOT NULL DEFAULT now(),
    usado boolean NOT NULL DEFAULT false
);
