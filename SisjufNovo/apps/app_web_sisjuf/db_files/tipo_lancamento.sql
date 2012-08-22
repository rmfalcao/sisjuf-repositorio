-- Table: tipo_lancamento

-- DROP TABLE tipo_lancamento;

CREATE TABLE tipo_lancamento
(
  seq_tipo_lancamento int2 NOT NULL,
  nom_tipo_lancamento varchar(30) NOT NULL,
  CONSTRAINT pk_tipo_lancamento PRIMARY KEY (seq_tipo_lancamento)
) 
WITHOUT OIDS;
ALTER TABLE tipo_lancamento OWNER TO admin_asserjuf;




