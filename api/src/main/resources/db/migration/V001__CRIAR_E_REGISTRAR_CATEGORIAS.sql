create table categoria(
    id serial primary key,
    nome varchar(100)
);

insert into categoria (nome) values ('Lazer');
insert into categoria (nome) values ('Alimentação');
insert into categoria (nome) values ('Supermercado');