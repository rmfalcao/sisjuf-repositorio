

begin transaction;

--insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Inserir') ,'Inserir diretor financeiro','Novo_DiretorFinanceiro');

-- atualizando entrada associada a novo_diretorfinanceiro para apontar para a tela de form vazia:
update funcao set seq_link = (select seq_link from link_funcao where url = '/financeiro/diretorFinanceiroForm.jsp'),
		  nome = 'Novo diretor financeiro'
where descricao = 'Novo_DiretorFinanceiro';

-- criando nova funcao para a servlet INSERIR:
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Inserir'),'Inserir diretor financeiro','Salva_DiretorFinanceiro');

-- associando essa nova função à funcionalidade de cadastrar diretor:
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where nome = 'Inserir diretor financeiro'),(select seq_funcionalidade from funcionalidade where descricao =  'Cadastrar diretor financeiro'));

commit;