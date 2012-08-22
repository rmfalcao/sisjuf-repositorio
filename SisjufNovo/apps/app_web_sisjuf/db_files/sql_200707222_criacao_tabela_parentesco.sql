CREATE TABLE parentesco
(
  seq_parentesco int2 NOT NULL,
  nom_parentesco varchar(40) NOT NULL, 
  CONSTRAINT pk_parentesco PRIMARY KEY (seq_parentesco)
) 
WITHOUT OIDS;

ALTER TABLE parentesco OWNER TO admin_asserjuf;

COMMENT ON TABLE parentesco IS 'parentescos de dependentes';
COMMENT ON COLUMN parentesco.seq_parentesco IS 'Chave de identificação da tabela parentesco';
COMMENT ON COLUMN parentesco.nom_parentesco IS 'Nome descritivo do parentesco.';


DROP VIEW vw_dependente;

alter table dependente 
drop column des_parentesco_dependente;

alter table dependente
add seq_parentesco int2;

alter table dependente
add CONSTRAINT fk_dependente_parentesco FOREIGN KEY (seq_parentesco)
      REFERENCES parentesco (seq_parentesco) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

CREATE OR REPLACE VIEW vw_dependente AS 
 SELECT p.seq_pessoa AS seq_dependente, p.nom_pessoa AS nom_dependente, p.dat_nascimento_pessoa AS dat_nascimento_dependente, 
	p.sts_sexo_pessoa AS sts_sexo_dependente, d.num_rg_dependente, d.seq_parentesco, pa.nom_parentesco as des_parentesco_dependente, d.seq_associado
   FROM pessoa p
NATURAL JOIN dependente d
NATURAL JOIN parentesco pa;

ALTER TABLE vw_dependente OWNER TO admin_asserjuf;

alter table dependente
alter seq_parentesco set not null;
