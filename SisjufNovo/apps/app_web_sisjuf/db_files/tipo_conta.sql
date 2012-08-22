-- Table: tipo_conta

-- DROP TABLE tipo_conta;

CREATE TABLE tipo_conta
(
  seq_tipo_conta int2 NOT NULL,
  des_tipo_conta varchar(20) NOT NULL,
  CONSTRAINT pk_tipo_conta PRIMARY KEY (seq_tipo_conta)
) 
WITHOUT OIDS;
ALTER TABLE tipo_conta OWNER TO admin_asserjuf;




