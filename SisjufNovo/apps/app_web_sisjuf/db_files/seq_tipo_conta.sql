-- Sequence: seq_tipo_conta

-- DROP SEQUENCE seq_tipo_conta;

CREATE SEQUENCE seq_tipo_conta
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 1
  CACHE 1;
ALTER TABLE seq_tipo_conta OWNER TO admin_asserjuf;
