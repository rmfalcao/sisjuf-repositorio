-- Table: conta_banco

-- DROP TABLE conta_banco;

CREATE TABLE conta_banco
(
  seq_conta int2 NOT NULL,
  seq_banco int2 NOT NULL,
  num_agencia_conta char(4) NOT NULL,
  dig_agencia_conta char(1),
  num_conta varchar(10) NOT NULL,
  dig_conta char(1),
  cod_operacao_conta char(3),
  seq_tipo_conta int2 NOT NULL,
  nom_titular_conta varchar(60),
  cpf_cnpj_titular_conta varchar(14),
  CONSTRAINT pk_conta_banco PRIMARY KEY (seq_conta),
  CONSTRAINT fk_conta_banco_conta FOREIGN KEY (seq_conta)
      REFERENCES conta (seq_conta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_conta_tipo_conta FOREIGN KEY (seq_tipo_conta)
      REFERENCES tipo_conta (seq_tipo_conta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE conta_banco OWNER TO admin_asserjuf;
