DROP VIEW vw_associado;

--------------

alter table associado
drop column des_endereco_orgao_associado;

alter table associado
add des_endereco_setor_associado varchar(100);

---------------

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, 
	p.sts_sexo_pessoa AS sts_sexo_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, 
	a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, 
	a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, 
	a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, 
	a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, 
	a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, 
	a.nom_municipio_setor_associado, a.des_endereco_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, 
	a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, a.seq_banco, 
	a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado
   FROM pessoa p
NATURAL JOIN associado a;

ALTER TABLE vw_associado OWNER TO postgres;