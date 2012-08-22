
ALTER TABLE LANCAMENTO DROP COLUMN TABELA_X;

ALTER TABLE LANCAMENTO DROP COLUMN NOM_CAMPO_X;

ALTER TABLE LANCAMENTO DROP COLUMN VAL_CAMPO_X;

CREATE TABLE FORMA_PAGAMENTO (
	SEQ_FORMA_PAGAMENTO	INT2 PRIMARY KEY NOT NULL,
	NOM_FORMA_PAGAMENTO	VARCHAR(30) NOT NULL
);

CREATE SEQUENCE SEQ_FORMA_PAGAMENTO START WITH 1 MAXVALUE 32000;

ALTER TABLE LANCAMENTO ADD SEQ_FORMA_PAGAMENTO INT2 NULL;

ALTER TABLE LANCAMENTO ADD CONSTRAINT FK_L_FORMA_PAGAMENTO FOREIGN KEY(SEQ_FORMA_PAGAMENTO) REFERENCES FORMA_PAGAMENTO (SEQ_FORMA_PAGAMENTO);

ALTER TABLE LANCAMENTO ADD DES_BANCO_CHEQUE_LANCAMENTO VARCHAR(30) NULL;

ALTER TABLE LANCAMENTO ADD NUM_AGENCIA_CHEQUE_LANCAMENTO VARCHAR(4) NULL;

ALTER TABLE LANCAMENTO ADD DIG_AGENCIA_CHEQUE_LANCAMENTO CHAR NULL;

ALTER TABLE LANCAMENTO ADD NUM_CONTA_CHEQUE_LANCAMENTO VARCHAR(30) NULL;

ALTER TABLE LANCAMENTO ADD DIG_CONTA_CHEQUE_LANCAMENTO CHAR NULL;

ALTER TABLE LANCAMENTO ADD DES_CHEQUE_LANCAMENTO VARCHAR(6) NULL;

----

CREATE TABLE DIRETOR_FINANCEIRO (
	SEQ_DIRETOR_FINANCEIRO	INT2 PRIMARY KEY NOT NULL,
	NOM_DIRETOR_FINANCEIRO	VARCHAR(50) NOT NULL,
	DAT_INICIO_GESTAO_DIRETOR_FINANCEIRO	DATE
);

----

ALTER TABLE DIRETOR_FINANCEIRO ALTER DAT_INICIO_GESTAO_DIRETOR_FINANCEIRO SET NOT NULL;

----

ALTER TABLE LANCAMENTO ALTER NUM_CONTA_CHEQUE_LANCAMENTO TYPE VARCHAR(10);

----

ALTER TABLE LANCAMENTO ALTER NUM_AGENCIA_CHEQUE_LANCAMENTO TYPE CHAR(4);

----

INSERT INTO FORMA_PAGAMENTO VALUES(NEXTVAL('SEQ_FORMA_PAGAMENTO'),'CHEQUE');

INSERT INTO FORMA_PAGAMENTO VALUES(NEXTVAL('SEQ_FORMA_PAGAMENTO'),'DINHEIRO');

INSERT INTO FORMA_PAGAMENTO VALUES(NEXTVAL('SEQ_FORMA_PAGAMENTO'),'DÉBITO AUTOMÁTICO');

INSERT INTO FORMA_PAGAMENTO VALUES(NEXTVAL('SEQ_FORMA_PAGAMENTO'),'DOC/TED');

INSERT INTO FORMA_PAGAMENTO VALUES(NEXTVAL('SEQ_FORMA_PAGAMENTO'),'TRANSFERÊNCIA');

INSERT INTO FORMA_PAGAMENTO VALUES(NEXTVAL('SEQ_FORMA_PAGAMENTO'),'PAGAMENTO VIA INTERNET BANKING');

----

INSERT INTO PARAMETROS VALUES ('FORMA_PAGTO_CHEQUE','1');

----

CREATE SEQUENCE SEQ_DIRETOR_FINANCEIRO START WITH 1 MAXVALUE 32000;


ALTER TABLE LANCAMENTO DROP COLUMN SEQ_FORMA_PAGAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD SEQ_FORMA_PAGAMENTO INT2;


ALTER TABLE LANCAMENTO DROP COLUMN DES_BANCO_CHEQUE_LANCAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD DES_BANCO_CHEQUE_BAIXA_LANCAMENTO VARCHAR(30);


ALTER TABLE LANCAMENTO DROP COLUMN NUM_AGENCIA_CHEQUE_LANCAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD NUM_AGENCIA_CHEQUE_BAIXA_LANCAMENTO VARCHAR(4);


ALTER TABLE LANCAMENTO DROP COLUMN DIG_AGENCIA_CHEQUE_LANCAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD DIG_AGENCIA_CHEQUE_BAIXA_LANCAMENTO CHAR;


ALTER TABLE LANCAMENTO DROP COLUMN NUM_CONTA_CHEQUE_LANCAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD NUM_CONTA_CHEQUE_BAIXA_LANCAMENTO VARCHAR(10);


ALTER TABLE LANCAMENTO DROP COLUMN DIG_CONTA_CHEQUE_LANCAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD DIG_CONTA_CHEQUE_BAIXA_LANCAMENTO CHAR;



ALTER TABLE LANCAMENTO DROP COLUMN DES_CHEQUE_LANCAMENTO;

ALTER TABLE BAIXA_LANCAMENTO ADD NUM_CHEQUE_BAIXA_LANCAMENTO VARCHAR(10);

ALTER TABLE BAIXA_LANCAMENTO ADD CONSTRAINT FK_BL_FORMA_PAGAMENTO FOREIGN KEY (SEQ_FORMA_PAGAMENTO) REFERENCES FORMA_PAGAMENTO (SEQ_FORMA_PAGAMENTO);

-- Function: obter_forma_pagamento(int8)

-- DROP FUNCTION obter_forma_pagamento(int8);

CREATE OR REPLACE FUNCTION obter_forma_pagamento(int8)
  RETURNS "varchar" AS
$BODY$
DECLARE
	id_lancamento ALIAS FOR $1;

	retorno varchar(255);
	registro RECORD;
	id_cheque int2;
BEGIN
	retorno := '';

	select into id_cheque cast(str_val_parametro as int2) from parametros where nom_parametro = 'FORMA_PAGTO_CHEQUE';	

	for registro in 
		select fp.nom_forma_pagamento, bl.seq_forma_pagamento, bl.des_banco_cheque_baixa_lancamento, 
		bl.num_agencia_cheque_baixa_lancamento,	bl.dig_agencia_cheque_baixa_lancamento, 
		bl.num_conta_cheque_baixa_lancamento, bl.dig_conta_cheque_baixa_lancamento, bl.num_cheque_baixa_lancamento
		from baixa_lancamento bl natural inner join forma_pagamento fp
		where bl.seq_lancamento = id_lancamento
		order by bl.dat_baixa_lancamento
	LOOP	

		retorno := retorno || registro.nom_forma_pagamento; 
		
		IF registro.seq_forma_pagamento = id_cheque THEN
			retorno := retorno || ' Nº' || registro.num_cheque_baixa_lancamento || ' ';
			retorno := retorno || registro.des_banco_cheque_baixa_lancamento || ' ' || registro.num_agencia_cheque_baixa_lancamento;
			IF (registro.dig_agencia_cheque_baixa_lancamento is not null) OR (registro.dig_agencia_cheque_baixa_lancamento <> '') THEN
				retorno := retorno || '-' || registro.dig_agencia_cheque_baixa_lancamento;
			END IF;
			retorno := retorno || '/' || registro.num_conta_cheque_baixa_lancamento || '-' || registro.dig_conta_cheque_baixa_lancamento;
		END IF;
		
		retorno := retorno || ', '; 

	END LOOP;

	IF retorno <> '' THEN
		retorno := substring(retorno from 1 for (char_length(retorno)-2) );
	ELSE
		retorno := null;
	END IF;
	
	RETURN retorno;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION obter_forma_pagamento(int8) OWNER TO admin_asserjuf;



update baixa_lancamento set seq_forma_pagamento = 2;

begin transaction;

insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Inserir','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Alterar','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Remover','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Detalhar','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/Financeiro/DiretorFinanceiro/Listar','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/financeiro/diretorFinanceiroForm.jsp','','i');
insert into link_funcao (seq_link, url, descricao, tipo) values (nextval('seq_link'),'/financeiro/diretorFinanceiroConsulta.jsp','','i');

insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Inserir') ,'Inserir diretor financeiro','Novo_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Alterar'),'Alterar diretor financeiro','Salva_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Remover'),'Remover diretor financeiro','Remove_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Detalhar'),'Detalhar diretor financeiro','Carrega_DiretorFinanceiro');
insert into funcao (seq_funcao, seq_link, nome, descricao) values (nextval('seq_funcao'), (select seq_link from link_funcao where url = '/Financeiro/DiretorFinanceiro/Listar'),'Listar diretor financeiro','Carrega_DiretorFinanceiro');

insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Cadastrar diretor financeiro','Cadastrar diretor financeiro');
insert into funcionalidade (seq_funcionalidade, nome, descricao) values (nextval('seq_funcionalidade'),'Detalhar diretor financeiro','Detalhar diretor financeiro');

insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Novo_DiretorFinanceiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Cadastrar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Salva_DiretorFinanceiro'),(select seq_funcionalidade from funcionalidade where descricao =  'Cadastrar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where descricao = 'Remove_DiretorFinanceiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Cadastrar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where nome = 'Detalhar diretor financeiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Detalhar diretor financeiro'));
insert into funcao_funcionalidade(seq_funcao, seq_funcionalidade) values ((select seq_funcao from funcao where nome = 'Listar diretor financeiro'),(select seq_funcionalidade from funcionalidade where descricao = 'Detalhar diretor financeiro'));

commit;
