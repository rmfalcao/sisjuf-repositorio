-- Table: tipo_operacao

-- DROP TABLE tipo_operacao;

CREATE TABLE tipo_operacao
(
  seq_tipo_operacao int2 NOT NULL,
  nom_tipo_operacao varchar(20) NOT NULL,
  sig_tipo_operacao char(2) NOT NULL,
  flg_previsao_tipo_operacao bool,
  CONSTRAINT pk_tipo_operacao PRIMARY KEY (seq_tipo_operacao)
) 
WITHOUT OIDS;
ALTER TABLE tipo_operacao OWNER TO admin_asserjuf;




