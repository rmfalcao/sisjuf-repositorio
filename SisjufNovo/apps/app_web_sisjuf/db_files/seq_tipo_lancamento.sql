-- Sequence: seq_tipo_lancamento

-- DROP SEQUENCE seq_tipo_lancamento;

CREATE SEQUENCE seq_tipo_lancamento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 5
  CACHE 1;
ALTER TABLE seq_tipo_lancamento OWNER TO admin_asserjuf;
