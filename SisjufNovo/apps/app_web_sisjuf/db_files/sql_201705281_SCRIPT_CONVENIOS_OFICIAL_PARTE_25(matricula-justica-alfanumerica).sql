--select * from associado where num_matricula_justica_associado is not null;

drop view vw_beneficiario;

DROP VIEW vw_associado;

ALTER TABLE ASSOCIADO ALTER COLUMN num_matricula_justica_associado TYPE VARCHAR(10);



-- View: vw_associado

-- DROP VIEW vw_associado;

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado,
    p.nom_pessoa AS nom_associado,
    p.dat_nascimento_pessoa AS dat_nascimento_associado,
    p.sts_sexo_pessoa AS sts_sexo_associado,
    a.num_rg_associado,
    a.num_cpf_associado,
    a.des_logradouro_associado,
    a.des_numero_endereco_associado,
    a.des_complemento_endereco_associado,
    a.des_bairro_associado,
    a.num_cep_associado,
    a.des_municipio_endereco_associado,
    a.seq_estado_endereco_associado,
    a.num_telefone_residencial_associado,
    a.num_telefone_comercial_associado,
    a.num_telefone_celular_associado,
    a.des_email_associado,
    a.des_naturalidade_associado,
    a.seq_estado_naturalidade_associado,
    a.sts_estado_civil_associado,
    a.des_profissao_associado,
    a.des_grupo_sanguineo_associado,
    a.sts_fator_rh_associado,
    a.nom_pai_associado,
    a.nom_mae_associado,
    a.nom_conjuge_associado,
    a.num_calcado_associado,
    a.sts_situacao_justica_associado,
    a.nom_orgao_associado,
    a.nom_setor_associado,
    a.seq_estado_setor_associado,
    a.nom_municipio_setor_associado,
    a.des_endereco_setor_associado,
    a.num_telefone_setor_associado,
    a.num_ramal_setor_associado,
    a.num_matricula_justica_associado,
    a.sts_recebe_jornal_associado,
    a.sts_categoria_associado,
    a.seq_contribuinte_associado,
    a.seq_banco,
    a.num_agencia_associado,
    a.dig_agencia_associado,
    a.num_conta_associado,
    a.dig_conta_associado,
    a.dat_exclusao_associado,
    a.sts_pre_cadastro_associado,
    t.seq_tipo_evento,
    t.nom_tipo_evento,
    h.dat_historico_evento_associado
   FROM pessoa p
     JOIN associado a USING (seq_pessoa)
     JOIN historico_evento_associado h ON a.seq_pessoa = h.seq_associado
     JOIN tipo_evento t USING (seq_tipo_evento)
  WHERE h.seq_historico_evento_associado = (( SELECT max(h2.seq_historico_evento_associado) AS max
           FROM historico_evento_associado h2
          WHERE h2.seq_associado = a.seq_pessoa AND (h2.seq_tipo_evento = ANY (ARRAY[1, 3]))));



-- View: vw_beneficiario

-- DROP VIEW vw_beneficiario;

CREATE OR REPLACE VIEW vw_beneficiario AS 
 SELECT vw_associado.seq_associado AS seq_beneficiario,
    vw_associado.seq_associado,
    vw_associado.nom_associado AS nom_beneficiario,
    vw_associado.num_rg_associado AS num_rg_beneficiario,
    vw_associado.num_cpf_associado AS num_cpf_beneficiario,
    'TITULAR'::text AS tipo,
    vw_associado.nom_associado AS nom_titular
   FROM vw_associado
UNION ALL
 SELECT vw_conjuge.seq_conjuge AS seq_beneficiario,
    vw_conjuge.seq_associado,
    vw_conjuge.nom_conjuge AS nom_beneficiario,
    vw_conjuge.num_rg_conjuge AS num_rg_beneficiario,
    vw_conjuge.num_cpf_conjuge AS num_cpf_beneficiario,
    'CONJUGE'::text AS tipo,
    titular.nom_pessoa AS nom_titular
   FROM vw_conjuge,
    pessoa titular
  WHERE vw_conjuge.seq_associado = titular.seq_pessoa
UNION ALL
 SELECT vw_dependente.seq_dependente AS seq_beneficiario,
    vw_dependente.seq_associado,
    vw_dependente.nom_dependente AS nom_beneficiario,
    vw_dependente.num_rg_dependente AS num_rg_beneficiario,
    vw_dependente.num_cpf_dependente AS num_cpf_beneficiario,
    'DEPENDENTE'::text AS tipo,
    titular.nom_pessoa AS nom_titular
   FROM vw_dependente,
    pessoa titular
  WHERE vw_dependente.seq_associado = titular.seq_pessoa
UNION ALL
 SELECT vw_filho.seq_filho AS seq_beneficiario,
    vw_filho.seq_associado,
    vw_filho.nom_filho AS nom_beneficiario,
    vw_filho.num_rg_filho AS num_rg_beneficiario,
    vw_filho.num_cpf_filho AS num_cpf_beneficiario,
    'FILHO'::text AS tipo,
    titular.nom_pessoa AS nom_titular
   FROM vw_filho,
    pessoa titular
  WHERE vw_filho.seq_associado = titular.seq_pessoa
UNION ALL
 SELECT vw_outro_beneficiavel.seq_outro AS seq_beneficiario,
    vw_outro_beneficiavel.seq_associado,
    vw_outro_beneficiavel.nom_outro AS nom_beneficiario,
    vw_outro_beneficiavel.num_rg_outro AS num_rg_beneficiario,
    vw_outro_beneficiavel.num_cpf_outro AS num_cpf_beneficiario,
    'OUTRO'::text AS tipo,
    titular.nom_pessoa AS nom_titular
   FROM vw_outro_beneficiavel,
    pessoa titular
  WHERE vw_outro_beneficiavel.seq_associado = titular.seq_pessoa;


