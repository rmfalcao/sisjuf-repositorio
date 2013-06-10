DROP VIEW VW_BENEFICIARIO;

CREATE OR REPLACE VIEW vw_beneficiario AS 
 SELECT vw_associado.seq_associado AS seq_beneficiario, vw_associado.seq_associado, vw_associado.nom_associado AS nom_beneficiario, vw_associado.num_rg_associado AS num_rg_beneficiario, vw_associado.num_cpf_associado AS num_cpf_beneficiario, 'TITULAR' AS tipo, vw_associado.nom_associado AS nom_titular
   FROM vw_associado
UNION ALL 
 SELECT vw_conjuge.seq_conjuge AS seq_beneficiario, vw_conjuge.seq_associado, vw_conjuge.nom_conjuge AS nom_beneficiario, vw_conjuge.num_rg_conjuge AS num_rg_beneficiario, vw_conjuge.num_cpf_conjuge AS num_cpf_beneficiario, 'CONJUGE' AS tipo, titular.NOM_PESSOA as nom_titular
   FROM vw_conjuge, pessoa titular
   WHERE VW_CONJUGE.SEQ_ASSOCIADO = TITULAR.SEQ_PESSOA
UNION ALL 
 SELECT vw_dependente.seq_dependente AS seq_beneficiario, vw_dependente.seq_associado, vw_dependente.nom_dependente AS nom_beneficiario, vw_dependente.num_rg_dependente AS num_rg_beneficiario, vw_dependente.num_cpf_dependente AS num_cpf_beneficiario, 'DEPENDENTE' AS tipo, titular.NOM_PESSOA as nom_titular
   FROM vw_dependente, pessoa titular
   WHERE vw_dependente.SEQ_ASSOCIADO = TITULAR.SEQ_PESSOA
UNION ALL 
 SELECT vw_filho.seq_filho AS seq_beneficiario, vw_filho.seq_associado, vw_filho.nom_filho AS nom_beneficiario, vw_filho.num_rg_filho AS num_rg_beneficiario, vw_filho.num_cpf_filho AS num_cpf_beneficiario, 'FILHO' AS tipo, titular.nom_PESSOA as nom_titular
   FROM vw_filho, pessoa titular
   WHERE vw_filho.SEQ_ASSOCIADO = TITULAR.SEQ_PESSOA
UNION ALL 
 SELECT vw_outro_beneficiavel.seq_outro AS seq_beneficiario, vw_outro_beneficiavel.seq_associado, vw_outro_beneficiavel.nom_outro AS nom_beneficiario, vw_outro_beneficiavel.num_rg_outro AS num_rg_beneficiario, vw_outro_beneficiavel.num_cpf_outro AS num_cpf_beneficiario, 'OUTRO' AS tipo, titular.nom_PESSOA as nom_titular
   FROM vw_outro_beneficiavel, pessoa titular
   WHERE vw_outro_beneficiavel.SEQ_ASSOCIADO = TITULAR.SEQ_PESSOA;


