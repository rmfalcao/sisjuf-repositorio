INSERT INTO funcionalidade (seq_funcionalidade, nome, descricao) VALUES (nextval('seq_funcionalidade'), 'Manter Convenios', 'Manter Convenios');

INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/convenioPesquisa.jsf', '/convenio/convenioPesquisa.jsf', 'i');
INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/convenioForm.jsf', '/associados/convenio/convenioForm.jsf', 'i');

INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/convenio/convenioPesquisa.jsf'), 'Consulta de Convenio', 'Consulta de Convenio');
INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/convenioForm.jsf'), 'Formulário de Convenio', 'Formulário de Convenio');

INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Consulta de Convenio'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Manter Convenios'));
INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Formulário de Convenio'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Manter Convenios'));

INSERT INTO perfil_funcionalidade (seq_perfil, seq_funcionalidade) VALUES ((SELECT seq_perfil FROM perfil WHERE nome='Administrador'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Manter Convenios'));

--ATUALIZAÇÃO DE LINKS PARA ATIVIDADE

INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/atividade/atividadePesquisa.jsf', '/associados/convenio/atividade/atividadePesquisa.jsf', 'i');
INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/atividade/atividadeForm.jsf', '/associados/convenio/atividade/atividadeForm.jsf', 'i');

INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/atividade/atividadePesquisa.jsf'), 'Consulta de Atividades', 'Consulta de Atividades');
INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/atividade/atividadeForm.jsf'), 'Formulário de Atividades', 'Formulário de Atividades');

INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Consulta de Atividades'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Manter Convenios'));
INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Formulário de Atividades'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Manter Convenios'));

update funcao set seq_link=47 where nome like '%Consulta de Convenio%';

---------------------------------------


INSERT INTO funcionalidade (seq_funcionalidade, nome, descricao) VALUES (nextval('seq_funcionalidade'), 'Faturas', 'Faturas');

INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/gerarFaturas.jsf', '/associados/convenio/gerarFaturas.jsf', 'i');

INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/gerarFaturas.jsf'), 'Tela para gerar faturas', 'Tela para gerar faturas');

INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Tela para gerar faturas'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Faturas'));

INSERT INTO perfil_funcionalidade (seq_perfil, seq_funcionalidade) VALUES ((SELECT seq_perfil FROM perfil WHERE nome='Administrador'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Faturas'));

