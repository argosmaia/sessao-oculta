-- -- Tabela principal com postgresql 18 nativo
-- CREATE TABLE filmes (
--     id              uuid PRIMARY KEY DEFAULT uuidv7(),
--     nome            text,
--     genero          text,
--     subgenero       text,
--     idade_minima    text,
--     descricao       text,
--     duracao         text,
--     capa_url        text,
--     nota_media      double precision,
--     data_lancamento date,
--     idioma_original text,
--     diretor         text,
--     pais_origem     text
-- );

-- -- Tabela secundária para @ElementCollection elenco
-- CREATE TABLE filme_elenco (
--     filme_id uuid NOT NULL REFERENCES filmes(id) ON DELETE CASCADE,
--     elenco   text,
--     PRIMARY KEY (filme_id, elenco)
-- );

CREATE EXTENSION IF NOT EXISTS pg_uuidv7;
-- Tabela principal
CREATE TABLE filmes (
    id              uuid PRIMARY KEY DEFAULT uuidv7(),
    nome            text,
    genero          text,
    subgenero       text,
    idade_minima    text,
    descricao       text,
    duracao         text,
    capa_url        text,
    nota_media      double precision,
    data_lancamento date,
    idioma_original text,
    diretor         text,
    pais_origem     text
);

-- Tabela secundária para @ElementCollection elenco
CREATE TABLE filme_elenco (
    filme_id uuid NOT NULL REFERENCES filmes(id) ON DELETE CASCADE,
    elenco   text,
    PRIMARY KEY (filme_id, elenco)
);
