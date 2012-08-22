ALTER TABLE FILHO ADD NUM_RG_FILHO BIGINT NULL;

-- MODIFICAR VIEW FILHO

DROP VIEW vw_filho;

CREATE OR REPLACE VIEW vw_filho AS 
 SELECT p.seq_pessoa AS seq_filho, p.nom_pessoa AS nom_filho, p.dat_nascimento_pessoa AS dat_nascimento_filho, p.sts_sexo_pessoa AS sts_sexo_filho, f.seq_associado, f.num_rg_filho, f.num_cpf_filho
   FROM pessoa p
NATURAL JOIN filho f;

-- MODIFICAR VIEW DEPENDENTE


DROP VIEW vw_dependente;

CREATE OR REPLACE VIEW vw_dependente AS 
 SELECT p.seq_pessoa AS seq_dependente, p.nom_pessoa AS nom_dependente, p.dat_nascimento_pessoa AS dat_nascimento_dependente, p.sts_sexo_pessoa AS sts_sexo_dependente, d.num_rg_dependente, d.seq_parentesco, pa.nom_parentesco AS des_parentesco_dependente, d.seq_associado, d.num_cpf_dependente
   FROM pessoa p
NATURAL JOIN dependente d
NATURAL JOIN parentesco pa;
