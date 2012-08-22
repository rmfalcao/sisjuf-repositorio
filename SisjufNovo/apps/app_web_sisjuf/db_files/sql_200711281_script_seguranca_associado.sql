insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoForm.jsf','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoPesquisa.jsf','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoEnviaEmail.jsf','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoEventos.jsf','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoAtualizaValor.jsf','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoAniversariantes.jsf','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/associadoImportarNucre.jsf','','i');


insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoForm.jsf') ,'Inserir associado','Novo_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoPesquisa.jsf') ,'Detalhar associado','Carrega_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoPesquisa.jsf') ,'Remover associado','Remove_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoPesquisa.jsf') ,'Alterar associado','Salva_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoPesquisa.jsf') ,'Imprimir associado','Imprime_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoPesquisa.jsf') ,'Filtrar associado','Filtrar_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoAniversariantes.jsf') ,'Listar aniversariantes','Carrega_Aniversariantes');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoAniversariantes.jsf') ,'Imprimir aniversariantes','Imprime_Aniversariantes');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoAniversariantes.jsf') ,'Enviar email aniversariantes','Envia_Email');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoPesquisa.jsf') ,'Enviar email associado','Envia_Email');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoEnviaEmail.jsf') ,'Enviar email','Envia_Email');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoEventos.jsf') ,'Filtrar evento','Filtrar_Historico');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoEventos.jsf') ,'Imprimir eventos','Imprime_historico');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoImportarNucre.jsf') ,'Importar arquivo nucre','Importar_Arquivo');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoAtualizaValor.jsf') ,'Alterar valor','Atualiza_Valor_Associado');
insert into funcao (seq_funcao, seq_link, nome, descricao) values 
(nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/associadoAtualizaValor.jsf') ,'Ver valor sócio usuário','Carrega_Valor_Associado');


insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Cadastrar associado','Cadastrar associado');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Consultar Associado','Consultar Associado');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Remover Associado','Remover Associado');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Alterar Associado','Alterar Associado');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Listar Aniversariantes','Listar Aniversariantes');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Enviar Email','Enviar Email');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Ver Histórico de Eventos','Ver Histórico de Eventos');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Importar Arquivo Nucre','Importar Arquivo Nucre');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Consultar Valor Sócio Usuário','Consultar Valor Sócio Usuário');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Alterar Valor Sócio Usuário','Alterar Valor Sócio Usuário');


insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Novo_Associado'),(select seq_funcionalidade from funcionalidade where descricao = 'Cadastrar associado'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Carrega_Associado'),(select seq_funcionalidade from funcionalidade where descricao =  'Consultar Associado'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Remove_Associado'),(select seq_funcionalidade from funcionalidade where descricao = 'Remover Associado'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Salva_Associado'),(select seq_funcionalidade from funcionalidade where descricao = 'Alterar Associado'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Imprime_Associado'),(select seq_funcionalidade from funcionalidade where descricao =  'Consultar Associado'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Filtrar_Associado'),(select seq_funcionalidade from funcionalidade where descricao = 'Consultar Associado'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Carrega_Aniversariantes'),(select seq_funcionalidade from funcionalidade where descricao =  'Listar Aniversariantes'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Imprime_Aniversariantes'),(select seq_funcionalidade from funcionalidade where descricao = 'Listar Aniversariantes'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where nome = 'Enviar email aniversariantes'),(select seq_funcionalidade from funcionalidade where descricao = 'Enviar Email'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where nome = 'Enviar email associado'),(select seq_funcionalidade from funcionalidade where descricao =  'Enviar Email'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where nome = 'Enviar email'),(select seq_funcionalidade from funcionalidade where descricao = 'Enviar Email'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Filtrar_Historico'),(select seq_funcionalidade from funcionalidade where descricao = 'Ver Histórico de Eventos'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Imprime_historico'),(select seq_funcionalidade from funcionalidade where descricao = 'Ver Histórico de Eventos'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Importar_Arquivo'),(select seq_funcionalidade from funcionalidade where descricao = 'Importar Arquivo Nucre'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Atualiza_Valor_Associado'),(select seq_funcionalidade from funcionalidade where descricao =  'Alterar Valor Sócio Usuário'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values 
((select seq_funcao from funcao where descricao = 'Carrega_Valor_Associado'),(select seq_funcionalidade from funcionalidade where descricao = 'Consultar Valor Sócio Usuário'));


