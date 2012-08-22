CREATE SEQUENCE seq_valor_mensalidade
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 7
  CACHE 1;
ALTER TABLE seq_valor_mensalidade OWNER TO postgres;