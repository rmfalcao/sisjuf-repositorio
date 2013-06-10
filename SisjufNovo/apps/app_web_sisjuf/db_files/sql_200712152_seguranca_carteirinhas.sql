
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Associados/ImprimirCarteirinha','','i');

insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Associados/ImprimirCarteirinha') ,'Imprimir carteirinha','carteirinha');


insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Imprimir carteirinha','Imprimir carteirinha');


insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'carteirinha'),(select seq_funcionalidade from funcionalidade where descricao = 'Imprimir carteirinha'));