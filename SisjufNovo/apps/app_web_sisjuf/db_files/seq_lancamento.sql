-- Sequence: seq_lancamento

-- DROP SEQUENCE seq_lancamento;

CREATE SEQUENCE seq_lancamento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_lancamento OWNER TO admin_asserjuf;
