-- Table: conta

-- DROP TABLE conta;

CREATE TABLE conta
(
  seq_conta int2 NOT NULL,
  nom_conta varchar(30), -- Nome da conta.
  CONSTRAINT pk_conta PRIMARY KEY (seq_conta)
) 
WITHOUT OIDS;
ALTER TABLE conta OWNER TO admin_asserjuf;
COMMENT ON COLUMN conta.nom_conta IS 'Nome da conta.';




