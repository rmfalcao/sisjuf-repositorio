-- Sequence: seq_origem_lancamento

-- DROP SEQUENCE seq_origem_lancamento;

CREATE SEQUENCE seq_origem_lancamento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 1
  CACHE 1;
ALTER TABLE seq_origem_lancamento OWNER TO admin_asserjuf;
