-- Sequence: seq_tipo_operacao

-- DROP SEQUENCE seq_tipo_operacao;

CREATE SEQUENCE seq_tipo_operacao
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 1
  CACHE 1;
ALTER TABLE seq_tipo_operacao OWNER TO admin_asserjuf;
