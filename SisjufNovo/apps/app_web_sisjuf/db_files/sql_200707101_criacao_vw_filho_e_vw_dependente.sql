CREATE OR REPLACE VIEW vw_filho AS 
 SELECT p.seq_pessoa AS seq_filho, p.nom_pessoa AS nom_filho, p.dat_nascimento_pessoa AS dat_nascimento_filho, 
	p.sts_sexo_pessoa AS sts_sexo_filho
   FROM pessoa p
NATURAL JOIN filho f;

ALTER TABLE vw_filho OWNER TO admin_asserjuf;

CREATE OR REPLACE VIEW vw_dependente AS 
 SELECT p.seq_pessoa AS seq_dependente, p.nom_pessoa AS nom_dependente, p.dat_nascimento_pessoa AS dat_nascimento_dependente, 
	p.sts_sexo_pessoa AS sts_sexo_dependente, d.num_rg_dependente, d.des_parentesco_dependente
   FROM pessoa p
NATURAL JOIN dependente d;

ALTER TABLE vw_dependente OWNER TO admin_asserjuf;