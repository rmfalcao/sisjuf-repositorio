-- Sequence: seq_banco

-- DROP SEQUENCE seq_banco;

CREATE SEQUENCE seq_banco
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 2
  CACHE 1;
ALTER TABLE seq_banco OWNER TO admin_asserjuf;
