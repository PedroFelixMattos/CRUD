create database agenda;

create table contatos(

rowid integer not null auto_increment primary key,
nome varchar(40),
idade integer,
dataCadastro date
)