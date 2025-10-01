CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

CREATE TABLE sessoes (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    filme_id uuid NOT NULL REFERENCES filmes(id) ON DELETE CASCADE,
    horario timestamp NOT NULL
);
