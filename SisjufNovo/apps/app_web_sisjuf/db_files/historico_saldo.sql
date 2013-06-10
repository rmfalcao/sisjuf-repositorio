-- Table: historico_saldo

-- DROP TABLE historico_saldo;

CREATE TABLE historico_saldo
(
  seq_conta int2 NOT NULL,
  dat_historico_saldo date NOT NULL,
  val_historico_saldo numeric(14,2) NOT NULL,
  CONSTRAINT pk_historico_saldo PRIMARY KEY (seq_conta, dat_historico_saldo),
  CONSTRAINT fk_historico_saldo_conta FOREIGN KEY (seq_conta)
      REFERENCES conta (seq_conta) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE historico_saldo OWNER TO admin_asserjuf;




