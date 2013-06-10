COMMENT ON TABLE CONVENIO IS 'Conv�nios da ASSERJUF.';
COMMENT ON COLUMN CONVENIO.SEQ_CONVENIO IS 'Chave de identifica��o do conv�nio.';
COMMENT ON COLUMN CONVENIO.FLG_CATEGORIA IS 'Categoria do conv�nio: "N" para "N�o gera fatura", "F" para "gera fatura Fixa" e "V" para "gera fatura Vari�vel".';
COMMENT ON COLUMN CONVENIO.NOM_FANTASIA IS 'Nome de fantasia do conv�nio.';
COMMENT ON COLUMN CONVENIO.NOM_RAZAO_SOCIAL IS 'Raz�o social do conv�nio.';
COMMENT ON COLUMN CONVENIO.SEQ_ATIVIDADE_CONVENIO IS 'Refer�ncia para a atividade do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_CNPJ IS 'N�mero do CNPJ do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_LOGRADOURO IS 'Logradouro do endere�o do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_COMPLEMENTO IS 'Complemento do endere�o do conv�nio.';
COMMENT ON COLUMN CONVENIO.NOM_BAIRRO IS 'Nome do bairro do conv�nio.';
COMMENT ON COLUMN CONVENIO.NOM_CIDADE IS 'Nome da cidade do conv�nio.';
COMMENT ON COLUMN CONVENIO.SEQ_ESTADO IS 'Refer�ncia para o estado (UF) do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_CEP IS 'CEP do endere�o do conv�nio.';
COMMENT ON COLUMN CONVENIO.NOM_CONTATO IS 'Nome da pessoa de contato do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_TELEFONE IS 'Telefone do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_TELEFONE_ADICIONAL IS 'Telefone alternativo do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_FAX IS 'N�mero do fax do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_EMAIL IS 'E-mail do conv�nio.';
COMMENT ON COLUMN CONVENIO.DES_BENEFICIO IS 'Descritivo do benef�cio do conv�nio para os associados da ASSERJUF.';
COMMENT ON COLUMN CONVENIO.DES_CONVENIO IS 'Descritivo do conv�nio.';
COMMENT ON COLUMN CONVENIO.NUM_DIA_FECHAMENTO IS 'N�mero do dia de fechamento do conv�nio.';
COMMENT ON COLUMN CONVENIO.NUM_FATOR_MES_VCTO IS 'Indica quantos meses (a partir do m�s do fechamento) ser� o vencimento das faturas dos conv�nios.';
COMMENT ON COLUMN CONVENIO.NUM_DIA_VCTO IS 'Dia de vencimento.';
COMMENT ON COLUMN CONVENIO.FLG_ATIVO IS 'Indica se o conv�nio est� ativo ("S") ou desativado ("N").';
COMMENT ON COLUMN CONVENIO.FLG_TITULAR IS 'Indica que um associado pode ser benefici�rio do conv�nio.';
COMMENT ON COLUMN CONVENIO.FLG_FILHOS IS 'Indica que filhos de um associado podem ser benefici�rios do conv�nio.';
COMMENT ON COLUMN CONVENIO.FLG_DEPENDENTES IS 'Indica que dependentes de um associado podem ser benefici�rios do conv�nio.';
COMMENT ON COLUMN CONVENIO.FLG_CONJUGE IS 'Indica que o conjuge de um associado pode ser benefici�rio do conv�nio.';
COMMENT ON COLUMN CONVENIO.FLG_OUTROS_BENEF IS 'Indica que outros benefici�veis de um associado podem ser benefici�rios do conv�nio.';


CREATE TABLE STATUS_FATURA (
  SEQ_STATUS_FATURA SMALLINT 	PRIMARY KEY 	NOT NULL,
  NOM_STATUS_FATURA VARCHAR(20) 		NOT NULL
);

ALTER TABLE STATUS_FATURA OWNER TO ASSERJUF;

------------------------


CREATE TABLE FATURA (
  SEQ_FATURA 		INT4		PRIMARY KEY	NOT NULL,
  SEQ_STATUS_FATURA 	SMALLINT			NOT NULL,
  SEQ_CONVENIO 		INT2 				NOT NULL,
  FLG_PAGO 		CHAR(1) 	DEFAULT 'N'	NOT NULL,
  FLG_RECEBIDO 		CHAR(1)		DEFAULT 'N'	NOT NULL,
  VAL_FATURA 		NUMERIC(14,2)			NULL,
  DAT_INICIO_FATURA 	DATE 				NOT NULL,
  DAT_FIM_FATURA  	DATE				NOT NULL
);

ALTER TABLE FATURA ADD CONSTRAINT FK_F_STATUS_FATURA FOREIGN KEY (SEQ_STATUS_FATURA) REFERENCES STATUS_FATURA (SEQ_STATUS_FATURA);
ALTER TABLE FATURA ADD CONSTRAINT FK_F_CONVENIO FOREIGN KEY (SEQ_CONVENIO) REFERENCES CONVENIO (SEQ_CONVENIO);
ALTER TABLE FATURA ADD CONSTRAINT CHK_FLG_PAGO CHECK (FLG_PAGO IN ('S','N'));
ALTER TABLE FATURA ADD CONSTRAINT CHK_FLG_RECEBIDO CHECK (FLG_RECEBIDO IN ('S','N'));


ALTER TABLE FATURA  OWNER TO ASSERJUF;

CREATE SEQUENCE SEQ_FATURA  START WITH 1 MAXVALUE 2147483647;

ALTER TABLE SEQ_FATURA  OWNER TO ASSERJUF;