begin transaction;



select * from link_funcao

insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Inserir','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Alterar','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Remover','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Detalhar','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Listar','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/financeiro/diretorFinanceiroForm.jsp','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/financeiro/diretorFinanceiroConsulta.jsp','','i');

select * from funcao order by descricao

insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Inserir') ,'Inserir diretor financeiro','Novo_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Alterar'),'Alterar diretor financeiro','Salva_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Remover'),'Remover diretor financeiro','Remove_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Detalhar'),'Detalhar diretor financeiro','Carrega_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Listar'),'Listar diretor financeiro','Carrega_DiretorFinanceiro');


select * from funcionalidade order by 2

insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Cadastrar diretor financeiro','Cadastrar diretor financeiro');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Detalhar diretor financeiro','Detalhar diretor financeiro');

select * from funcao_funcionalidade

insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Novo_DiretorFinanceiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Cadastrar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Salva_DiretorFinanceiro'),(select seq_funcionalidade from funcionalidade where descricao =  'Cadastrar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Remove_DiretorFinanceiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Cadastrar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where nome = 'Detalhar diretor financeiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Detalhar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where nome = 'Listar diretor financeiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Detalhar diretor financeiro'));

commit;