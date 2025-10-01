CREATE EXTENSION IF NOT EXISTS pg_uuidv7;

CREATE TABLE produtos (
    id uuid PRIMARY KEY DEFAULT uuidv7(),
    nome        text NOT NULL,
    descricao   text,
    lanche      text CHECK (lanche IN ('PIPOCA','PIPOCA_DOCE','PAO_DE_QUEIJO','TWIX','NACHO','BATATA_FRITAS')),
    bebida      text CHECK (bebida IN ('COCA_COLA','PEPSI','GUARAVITA','GUARANA','MONSTER','GRAPETTE','ICE_TEA')),
    preco       numeric(10,2) NOT NULL
);

