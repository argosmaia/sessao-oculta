-- CREATE TABLE assentos (
--     id        uuid PRIMARY KEY DEFAULT uuidv7(),
--     codigo    text NOT NULL,

--     usuario_id uuid NOT NULL,
--     sessao_id  uuid NOT NULL,
--     sala_id    uuid NOT NULL,

--     CONSTRAINT fk_assento_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
--     CONSTRAINT fk_assento_sessao  FOREIGN KEY (sessao_id)  REFERENCES sessoes  (id) ON DELETE CASCADE,
--     CONSTRAINT fk_assento_sala    FOREIGN KEY (sala_id)    REFERENCES salas    (id) ON DELETE CASCADE
-- );

CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

CREATE TABLE assentos (
    id        uuid PRIMARY KEY DEFAULT uuidv7(),
    codigo    text NOT NULL,

    usuario_id uuid NOT NULL,
    sessao_id  uuid NOT NULL,
    sala_id    uuid NOT NULL,

    CONSTRAINT fk_assento_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios (id) ON DELETE CASCADE,
    CONSTRAINT fk_assento_sessao  FOREIGN KEY (sessao_id)  REFERENCES sessoes  (id) ON DELETE CASCADE,
    CONSTRAINT fk_assento_sala    FOREIGN KEY (sala_id)    REFERENCES salas    (id) ON DELETE CASCADE
);
