-- Table: origem_lancamento

-- DROP TABLE origem_lancamento;

CREATE TABLE origem_lancamento
(
  seq_origem_lancamento int2 NOT NULL,
  nom_origem_lancamento varchar(20) NOT NULL,
  flg_ativo_origem_lancamento bool,
  CONSTRAINT pk_origem_lancamento PRIMARY KEY (seq_origem_lancamento)
) 
WITHOUT OIDS;
ALTER TABLE origem_lancamento OWNER TO admin_asserjuf;
