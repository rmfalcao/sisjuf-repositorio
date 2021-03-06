ALTER TABLE ORIGEM_LANCAMENTO ADD FLG_NAO_APAGAVEL BOOL DEFAULT FALSE NOT NULL;

INSERT INTO ORIGEM_LANCAMENTO (SEQ_ORIGEM_LANCAMENTO, NOM_ORIGEM_LANCAMENTO, FLG_ATIVO_ORIGEM_LANCAMENTO, FLG_NAO_APAGAVEL) VALUES (0, 'USU�RIO', TRUE, TRUE);

----------------

ALTER TABLE TIPO_LANCAMENTO ADD FLG_NAO_APAGAVEL BOOL DEFAULT FALSE NOT NULL;

INSERT INTO TIPO_LANCAMENTO (SEQ_TIPO_LANCAMENTO, NOM_TIPO_LANCAMENTO, FLG_NAO_APAGAVEL) VALUES (0, 'MOVIMENTA��O ENTRE CONTAS',TRUE);

----------------

ALTER TABLE TIPO_OPERACAO ADD FLG_DEBITO BOOL DEFAULT FALSE NOT NULL;

INSERT INTO TIPO_OPERACAO(SEQ_TIPO_OPERACAO, NOM_TIPO_OPERACAO, SIG_TIPO_OPERACAO, FLG_PREVISAO_TIPO_OPERACAO, FLG_DEBITO) VALUES (1, 'CR�DITO', 'C', FALSE, FALSE);
INSERT INTO TIPO_OPERACAO(SEQ_TIPO_OPERACAO, NOM_TIPO_OPERACAO, SIG_TIPO_OPERACAO, FLG_PREVISAO_TIPO_OPERACAO, FLG_DEBITO) VALUES (2, 'D�BITO', 'D', FALSE, TRUE);
INSERT INTO TIPO_OPERACAO(SEQ_TIPO_OPERACAO, NOM_TIPO_OPERACAO, SIG_TIPO_OPERACAO, FLG_PREVISAO_TIPO_OPERACAO, FLG_DEBITO) VALUES (3, 'A CREDITAR', 'AC', FALSE, FALSE);
INSERT INTO TIPO_OPERACAO(SEQ_TIPO_OPERACAO, NOM_TIPO_OPERACAO, SIG_TIPO_OPERACAO, FLG_PREVISAO_TIPO_OPERACAO, FLG_DEBITO) VALUES (4, 'A DEBITAR', 'AD', FALSE, TRUE);

----------------

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('ORIGEM_USUARIO','0');
INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('TP_LANC_MOVIMENTACAO','0');
INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('TP_OPERACAO_DEBITO','2');
INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('TP_OPERACAO_CREDITO','1');
