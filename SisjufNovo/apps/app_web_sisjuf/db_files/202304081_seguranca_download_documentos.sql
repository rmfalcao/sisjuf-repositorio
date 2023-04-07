-- select * from link_funcao;

-- select * from funcao_funcionalidade;

insert into link_funcao (seq_link, url, descricao, tipo) values
(61,'/Associados/Documento/Download', 'Download de documento de associado', 'i');

insert into funcao (seq_funcao, seq_link, nome, descricao) values
(87, 61, 'Download de documento de associado', 'Download de documento de associado');

insert into funcionalidade(seq_funcionalidade, nome, descricao) values
(60, 'Download de documento de associado', 'Download de documento de associado');


insert into funcao_funcionalidade (seq_funcao, seq_funcionalidade) values 
(87,60);

-- select * from perfil_funcionalidade;
