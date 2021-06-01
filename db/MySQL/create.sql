drop database siteempregos;

create database siteempregos;

use siteempregos;

create table Empresa(
	email varchar(30) not null, 
	senha varchar(20) not null, 
	cnpj bigint not null, 
	nome varchar(50) not null, 
	descricao varchar(256) not null, 
	cidade varchar(30) not null, 
	primary key (cnpj)
);
    
insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values ('empresaa@gmail.com', '12345', '38123657000100', 'Empresa A', 'Empresa de cosméticos, como cremes e protetores solares', 'Rio de Janeiro');
insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values ('empresab@gmail.com', '67890', '45133125000178', 'Empresa B', 'Empresa de alimentos, como biscoitos e panetones', 'Campinas');
insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values ('empresac@gmail.com', 'senha123', '12102873000145', 'Empresa C', 'Empresa de consultoria', 'São Paulo');
insert into Empresa(email, senha, cnpj, nome, descricao, cidade) values ('empresad@gmail.com', 'senhasenha', '44321453000180', 'Empresa D', 'Empresa automobilistica', 'Manaus');

create table Profissional( 
    email varchar(30) not null, 
    senha varchar(20) not null, 
    cpf bigint not null, 
    nome varchar(50) not null, 
    telefone varchar(15) not null, 
    sexo varchar(1) not null, 
    nascimento date not null, 
    primary key (cpf)
);
	

insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('ana@gmail.com', 'senha', 13255476510, 'Ana Maria Silva', '(11)2340-3243', 'F', '2000-10-20');
insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('claudia@gmail.com', 'anajulia', 01527229041, 'Claudia Martins', '(19)3324-3243', 'F', '1976-03-15');
insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('thais@gmail.com', 'aaabbb', 49960352005, 'Tais Lima', '(22)1235-8345', 'F', '1998-11-22');
insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('cassia@gmail.com', 'password', 97440409043, 'Daniela Mendes', '(19)3254-8643', 'F', '1998-11-22');
insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('mario@gmail.com', 'senha1234', 90309115060, 'Mario Medeiros', '(35)99234-1265', 'M', '1970-11-30');
insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('heitor@gmail.com', 'abc123', 61324432071, 'Heitor Lucas Duarte', '(11)99872-3012', 'M', '1992-08-10');
insert into Profissional(email, senha, cpf, nome, telefone, sexo, nascimento) values ('admin', 'admin', 1231231230, 'Administrador do sistema', '(99)91234-5678', 'M', '1999-09-29');

create table Vaga( 
    id bigint not null auto_increment,
    nome varchar(60) not null, 
    status varchar(20) not null, 
    descricao varchar(256) not null, 
    salario float not null, 
    data_limite date not null, 
    empresa bigint not null, 
    primary key(id), 
    foreign key (empresa) references Empresa(cnpj) ON DELETE CASCADE
);

insert into Vaga(nome, status, descricao, salario, data_limite, empresa) values ('Auxiliar de laboratório', 'Aberta', 'Auxilia atividades no laboratorio, faz limpeza dos materiais', 2000.00, '2021-10-30', 38123657000100);
insert into Vaga(nome, status, descricao, salario, data_limite, empresa) values ('Coordenador de produtos', 'Aberta', 'Coordena toda a equipe de Produtos', 10000.00, '2021-11-21', 38123657000100);
insert into Vaga(nome, status, descricao, salario, data_limite, empresa) values ('Auxiliar geral', 'Aberta', 'Auxilia todos os funcionarios no que for necessario', 15200.00, '2021-08-01', 45133125000178);
insert into Vaga(nome, status, descricao, salario, data_limite, empresa) values ('Consultor financeiro', 'Aberta', 'Faz consultas sobre o financeiro da empresa', 1500.00, '2021-05-31', 12102873000145);
insert into Vaga(nome, status, descricao, salario, data_limite, empresa) values ('Coordenador Geral', 'Aberta', 'Coordena uma equipe de 50 pessoas, deve poder viajar', 3000.00, '2020-07-30', 44321453000180);


create table Inscricao(
    id bigint not null auto_increment, 
    cv varchar(100) not null, 
    status varchar(20) not null, 
    data_inscricao date not null, 
    profissional bigint not null, 
    vaga bigint not null, 
    primary key(id),  
    foreign key(profissional) references Profissional(cpf) ON DELETE CASCADE, 
    foreign key(vaga) references Vaga(id) ON DELETE CASCADE
); 
    
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculo.com.br', 'ABERTO', '2021-01-06', 13255476510, 3);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculo2.com.br', 'ABERTO', '2021-02-05', 01527229041, 2);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculo3.com.br', 'ABERTO', '2021-05-10', 49960352005, 5);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculo4.com.br', 'NAO SELECIONADO', '2021-08-20', 97440409043, 4);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculo5.com.br', 'ABERTO', '2021-07-25', 90309115060, 3);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://cv.com.br', 'ENTREVISTA', '2021-03-25', 13255476510, 4);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculumvitae.com.br', 'ABERTO', '2021-06-03', 01527229041, 3);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://cv_a.com.br', 'ABERTO', '2021-05-26', 49960352005, 5);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://curriculov.com.br', 'ABERTO', '2021-06-27', 97440409043, 5);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://cv1.com.br', 'ABERTO', '2021-05-20', 90309115060, 1);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://cv2.com.br', 'ABERTO', '2021-05-20', 90309115060, 2);
insert into Inscricao(cv, status, data_inscricao, profissional, vaga) values ('https://cv_meu.com.br', 'NAO SELECIONADO', '2021-05-20', 61324432071, 1);