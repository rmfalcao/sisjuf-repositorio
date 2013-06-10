-- Sequence: seq_conta

-- DROP SEQUENCE seq_conta;

CREATE SEQUENCE seq_conta
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 1
  CACHE 1;
ALTER TABLE seq_conta OWNER TO admin_asserjuf;
