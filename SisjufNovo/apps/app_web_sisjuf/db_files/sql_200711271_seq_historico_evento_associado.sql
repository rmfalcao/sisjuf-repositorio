alter table historico_evento_associado
add seq_historico_evento_associado int4 default 1 not null;

CREATE SEQUENCE seq_historico_evento_associado
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 2147483647
  START 10
  CACHE 1;
ALTER TABLE seq_historico_evento_associado OWNER TO admin_asserjuf;


select * from historico_evento_associado order by seq_historico_evento_associado;