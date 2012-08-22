-- Table: lancamento

-- DROP TABLE lancamento;

CREATE TABLE lancamento
(
  seq_lancamento int8 NOT NULL,
  seq_conta int2 NOT NULL,
  seq_origem_lancamento int2 NOT NULL,
  seq_tipo_lancamento int2,
  seq_tipo_operacao int2 NOT NULL,
  dat_previsao_lancamento date,
  val_lancamento numeric(14,2) NOT NULL,
  des_lancamento varchar(160),
  tabela_x varchar(30),
  nom_campo_x varchar(30),
  val_campo_x varchar(30),
  CONSTRAINT pk_lancamento PRIMARY KEY (seq_lancamento),
  CONSTRAINT fk_lancamento_conta FOREIGN KEY (seq_conta)
      REFERENCES conta (seq_conta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_lancamento_origem_lancamento FOREIGN KEY (seq_origem_lancamento)
      REFERENCES origem_lancamento (seq_origem_lancamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_lancamento_tipo_lancamento FOREIGN KEY (seq_tipo_lancamento)
      REFERENCES tipo_lancamento (seq_tipo_lancamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_lancamento_tipo_operacao FOREIGN KEY (seq_tipo_operacao)
      REFERENCES tipo_operacao (seq_tipo_operacao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE lancamento OWNER TO admin_asserjuf;




