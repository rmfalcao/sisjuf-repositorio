-- View: "vw_conta"

-- DROP VIEW vw_conta;

CREATE OR REPLACE VIEW vw_conta AS 
 SELECT c.seq_conta, c.nom_conta, cb.seq_banco, cb.num_agencia_conta, cb.dig_agencia_conta, cb.num_conta, cb.dig_conta, cb.cod_operacao_conta, cb.seq_tipo_conta, cb.nom_titular_conta, cb.cpf_cnpj_titular_conta
   FROM conta c
NATURAL LEFT JOIN conta_banco cb;

ALTER TABLE vw_conta OWNER TO admin_asserjuf;
