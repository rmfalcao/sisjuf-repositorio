INSERT INTO funcionalidade (seq_funcionalidade, nome, descricao) VALUES (nextval('seq_funcionalidade'), 'Faturas', 'Faturas');

INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/gerarFaturas.jsf', '/associados/convenio/gerarFaturas.jsf', 'i');

INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/gerarFaturas.jsf'), 'Tela para gerar faturas', 'Tela para gerar faturas');

INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Tela para gerar faturas'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Faturas'));

INSERT INTO perfil_funcionalidade (seq_perfil, seq_funcionalidade) VALUES ((SELECT seq_perfil FROM perfil WHERE nome='Administrador'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Faturas'));


INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/faturaPesquisa.jsf', '/associados/convenio/faturaPesquisa.jsf', 'i');

INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/faturaPesquisa.jsf'), 'Tela para pesquisar faturas', 'Tela para pesquisar faturas');

INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Tela para pesquisar faturas'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Faturas'));


INSERT INTO link_funcao (seq_link, url, descricao, tipo) VALUES (nextval('seq_link'), '/associados/convenio/gerarFaturasFixas.jsf', '/associados/convenio/gerarFaturasFixas.jsf', 'i');

INSERT INTO funcao (seq_funcao, seq_link, nome, descricao) VALUES (nextval('seq_funcao'), (SELECT seq_link FROM link_funcao WHERE url='/associados/convenio/gerarFaturasFixas.jsf'), 'Tela para gerar faturas fixas', 'Tela para gerar faturas fixas');

INSERT INTO funcao_funcionalidade (seq_funcao, seq_funcionalidade) VALUES ((SELECT seq_funcao FROM funcao WHERE nome='Tela para gerar faturas fixas'), (SELECT seq_funcionalidade FROM funcionalidade WHERE nome='Faturas'));

