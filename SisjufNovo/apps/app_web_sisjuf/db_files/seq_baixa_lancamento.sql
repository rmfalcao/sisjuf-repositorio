-- Sequence: seq_baixa_lancamento

-- DROP SEQUENCE seq_baixa_lancamento;

CREATE SEQUENCE seq_baixa_lancamento
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE seq_baixa_lancamento OWNER TO admin_asserjuf;
