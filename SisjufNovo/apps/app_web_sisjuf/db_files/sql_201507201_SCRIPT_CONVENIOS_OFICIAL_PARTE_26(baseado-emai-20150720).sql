ALTER TABLE VINCULACAO_PLANO ADD CODIGO_BENEFICIARIO_PLANO VARCHAR(50);

ALTER TABLE ITEM_FATURA_ARQUIVO ADD CODIGO_BENEFICIARIO_PLANO VARCHAR(50);

-- scripts elaborados por AMORIM em 12/07/2015

insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/associados/convenio/faturaDetalhada.jsf','','i');

insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/associados/convenio/faturaDetalhada.jsf') ,'Detalhamento de fatura', 'Detalhamento de fatura');

insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Processamento de Fatura','Processamento de Fatura');

insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Detalhamento de fatura'),(select seq_funcionalidade from funcionalidade where descricao = 'Processamento de Fatura'));

INSERT INTO perfil_funcionalidade (seq_perfil, seq_funcionalidade) VALUES (8, (select seq_funcionalidade from funcionalidade where descricao = 'Processamento de Fatura'));

INSERT INTO perfil_funcionalidade (seq_perfil, seq_funcionalidade) VALUES (21, (select seq_funcionalidade from funcionalidade where descricao = 'Processamento de Fatura'));

ALTER TABLE ITEM_FATURA_ARQUIVO ALTER COLUMN num_cpf_beneficiario DROP NOT NULL;

-- fim scripts AMORIM

update vinculacao_plano set codigo_beneficiario_plano = codigo_beneficiario_plano || seq_vinculacao || seq_pessoa;