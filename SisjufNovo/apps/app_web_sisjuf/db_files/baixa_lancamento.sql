-- Table: baixa_lancamento

-- DROP TABLE baixa_lancamento;

CREATE TABLE baixa_lancamento
(
  seq_baixa_lancamento int8 NOT NULL,
  seq_lancamento int8 NOT NULL,
  dat_baixa_lancamento date NOT NULL,
  val_baixa_lancamento numeric(14,2) NOT NULL,
  CONSTRAINT pk_baixa_lancamento PRIMARY KEY (seq_baixa_lancamento),
  CONSTRAINT fk_baixa_lancamento_lancamento FOREIGN KEY (seq_lancamento)
      REFERENCES lancamento (seq_lancamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE baixa_lancamento OWNER TO admin_asserjuf;




