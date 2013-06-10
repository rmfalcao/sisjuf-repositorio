COMMENT ON TABLE CONVENIO IS 'Convênios da ASSERJUF.';
COMMENT ON COLUMN CONVENIO.SEQ_CONVENIO IS 'Chave de identificação do convênio.';
COMMENT ON COLUMN CONVENIO.FLG_CATEGORIA IS 'Categoria do convênio: "N" para "Não gera fatura", "F" para "gera fatura Fixa" e "V" para "gera fatura Variável".';
COMMENT ON COLUMN CONVENIO.NOM_FANTASIA IS 'Nome de fantasia do convênio.';
COMMENT ON COLUMN CONVENIO.NOM_RAZAO_SOCIAL IS 'Razão social do convênio.';
COMMENT ON COLUMN CONVENIO.SEQ_ATIVIDADE_CONVENIO IS 'Referência para a atividade do convênio.';
COMMENT ON COLUMN CONVENIO.DES_CNPJ IS 'Número do CNPJ do convênio.';
COMMENT ON COLUMN CONVENIO.DES_LOGRADOURO IS 'Logradouro do endereço do convênio.';
COMMENT ON COLUMN CONVENIO.DES_COMPLEMENTO IS 'Complemento do endereço do convênio.';
COMMENT ON COLUMN CONVENIO.NOM_BAIRRO IS 'Nome do bairro do convênio.';
COMMENT ON COLUMN CONVENIO.NOM_CIDADE IS 'Nome da cidade do convênio.';
COMMENT ON COLUMN CONVENIO.SEQ_ESTADO IS 'Referência para o estado (UF) do convênio.';
COMMENT ON COLUMN CONVENIO.DES_CEP IS 'CEP do endereço do convênio.';
COMMENT ON COLUMN CONVENIO.NOM_CONTATO IS 'Nome da pessoa de contato do convênio.';
COMMENT ON COLUMN CONVENIO.DES_TELEFONE IS 'Telefone do convênio.';
COMMENT ON COLUMN CONVENIO.DES_TELEFONE_ADICIONAL IS 'Telefone alternativo do convênio.';
COMMENT ON COLUMN CONVENIO.DES_FAX IS 'Número do fax do convênio.';
COMMENT ON COLUMN CONVENIO.DES_EMAIL IS 'E-mail do convênio.';
COMMENT ON COLUMN CONVENIO.DES_BENEFICIO IS 'Descritivo do benefício do convênio para os associados da ASSERJUF.';
COMMENT ON COLUMN CONVENIO.DES_CONVENIO IS 'Descritivo do convênio.';
COMMENT ON COLUMN CONVENIO.NUM_DIA_FECHAMENTO IS 'Número do dia de fechamento do convênio.';
COMMENT ON COLUMN CONVENIO.NUM_FATOR_MES_VCTO IS 'Indica quantos meses (a partir do mês do fechamento) será o vencimento das faturas dos convênios.';
COMMENT ON COLUMN CONVENIO.NUM_DIA_VCTO IS 'Dia de vencimento.';
COMMENT ON COLUMN CONVENIO.FLG_ATIVO IS 'Indica se o convênio está ativo ("S") ou desativado ("N").';
COMMENT ON COLUMN CONVENIO.FLG_TITULAR IS 'Indica que um associado pode ser beneficiário do convênio.';
COMMENT ON COLUMN CONVENIO.FLG_FILHOS IS 'Indica que filhos de um associado podem ser beneficiários do convênio.';
COMMENT ON COLUMN CONVENIO.FLG_DEPENDENTES IS 'Indica que dependentes de um associado podem ser beneficiários do convênio.';
COMMENT ON COLUMN CONVENIO.FLG_CONJUGE IS 'Indica que o conjuge de um associado pode ser beneficiário do convênio.';
COMMENT ON COLUMN CONVENIO.FLG_OUTROS_BENEF IS 'Indica que outros beneficiáveis de um associado podem ser beneficiários do convênio.';


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