-- -- Cria schema / extensão padrão (não precisa instalar pg_uuidv7)
-- -- (Postgres 18 já tem função uuidv7())
-- CREATE TABLE usuarios (
--   id    uuid PRIMARY KEY DEFAULT uuidv7(),
--   nome  text,
--   idade integer,
--   aniversario date,
--   cpf   text UNIQUE NOT NULL,
--   email text UNIQUE NOT NULL,
--   senha text,
--   telefone text,
--   -- campos do endereco podem ser normalizados em outra tabela ou embutidos como jsonb
--   endereco jsonb
-- );

-- -- exemplo de alteração em tabela já existente:
-- ALTER TABLE usuarios
--   ALTER COLUMN id SET DEFAULT uuidv7();


CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

CREATE TABLE usuarios (
  id uuid PRIMARY KEY DEFAULT uuidv7(),
  nome text,
  idade integer,
  aniversario date,
  cpf text UNIQUE NOT NULL,
  email text UNIQUE NOT NULL,
  senha text,
  telefone text,
  endereco jsonb
);
