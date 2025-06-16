    create table produtos (
        id uuid not null,
        bebida varchar(255) check (bebida in ('COCACOLA','PEPSI','GUARAVITA','GUARANA')),
        descricao varchar(255),
        lanche varchar(255) check (lanche in ('PIPOCA','PIPOCA_DOCE','PAO_DE_QUEIJO')),
        nome varchar(255),
        primary key (id)
    )