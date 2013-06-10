
CREATE SEQUENCE SEQ_PESSOA START WITH 1 MAXVALUE 2147483647;

ALTER TABLE SEQ_PESSOA OWNER TO admin_asserjuf;

CREATE SEQUENCE SEQ_MUNICIPIO START WITH 1 MAXVALUE 32767;

ALTER TABLE SEQ_MUNICIPIO OWNER TO admin_asserjuf;

CREATE SEQUENCE SEQ_ORGAO START WITH 1 MAXVALUE 32767;

ALTER TABLE SEQ_ORGAO OWNER TO admin_asserjuf;

CREATE SEQUENCE SEQ_PROFISSAO START WITH 1 MAXVALUE 32767;

ALTER TABLE SEQ_PROFISSAO OWNER TO admin_asserjuf;

CREATE SEQUENCE SEQ_SETOR START WITH 1 MAXVALUE 32767;

ALTER TABLE SEQ_SETOR OWNER TO admin_asserjuf;

-----------------

CREATE TABLE pessoa
(
  seq_pessoa int4 NOT NULL, -- Chave de identificação de pessoa.
  nom_pessoa varchar(60) NOT NULL, -- Nome da pessoa.
  dat_nascimento_pessoa date NOT NULL, -- Data de nascimento.
  sts_sexo_pessoa char(1) NOT NULL, -- Sexo.
  CONSTRAINT pk_pessoa PRIMARY KEY (seq_pessoa)
) 
WITHOUT OIDS;
ALTER TABLE pessoa OWNER TO admin_asserjuf;
COMMENT ON COLUMN pessoa.seq_pessoa IS 'Chave de identificação de pessoa.';
COMMENT ON COLUMN pessoa.nom_pessoa IS 'Nome da pessoa.';
COMMENT ON COLUMN pessoa.dat_nascimento_pessoa IS 'Data de nascimento.';
COMMENT ON COLUMN pessoa.sts_sexo_pessoa IS 'Sexo.';



---------------------------------------


CREATE TABLE dependente
(
  seq_pessoa int4 NOT NULL, -- Chave de identificação de pessoa.
  num_rg_dependente int8 NOT NULL, -- RG do dependente.
  des_parentesco_dependente varchar(30) NOT NULL, -- Parentesco do dependente.
  CONSTRAINT pk_dependente PRIMARY KEY (seq_pessoa),
  CONSTRAINT fk_dependente_pessoa FOREIGN KEY (seq_pessoa)
      REFERENCES pessoa (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE dependente OWNER TO admin_asserjuf;
COMMENT ON TABLE dependente IS 'Dependentes dos associados da Asserjuf.';
COMMENT ON COLUMN dependente.seq_pessoa IS 'Chave de identificação de pessoa.';
COMMENT ON COLUMN dependente.num_rg_dependente IS 'RG do dependente.';
COMMENT ON COLUMN dependente.des_parentesco_dependente IS 'Parentesco do dependente.';


--------------------------------------

CREATE TABLE estado
(
  seq_estado int2 NOT NULL, -- Chave de identificação da tabela estado
  nom_estado varchar(40) NOT NULL, -- Nome do estado.
  sig_estado char(2) NOT NULL, -- Sigla do estado
  CONSTRAINT pk_estado PRIMARY KEY (seq_estado)
) 
WITHOUT OIDS;
ALTER TABLE estado OWNER TO admin_asserjuf;
COMMENT ON TABLE estado IS 'Estados brasileiros';
COMMENT ON COLUMN estado.seq_estado IS 'Chave de identificação da tabela estado';
COMMENT ON COLUMN estado.nom_estado IS 'Nome do estado.';
COMMENT ON COLUMN estado.sig_estado IS 'Sigla do estado';




--------------------------------------



CREATE TABLE filho
(
  seq_pessoa int4 NOT NULL, -- Chave de identificação de pessoa.
  CONSTRAINT pk_filho PRIMARY KEY (seq_pessoa),
  CONSTRAINT fk_filho_pessoa FOREIGN KEY (seq_pessoa)
      REFERENCES pessoa (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE filho OWNER TO admin_asserjuf;
COMMENT ON TABLE filho IS 'filhos dos associados da Asserjuf.';
COMMENT ON COLUMN filho.seq_pessoa IS 'Chave de identificação de pessoa.';




-------------------------------------


CREATE TABLE municipio
(
  seq_municipio int2 NOT NULL, -- chave de identificação do município
  nom_municipio varchar(50) NOT NULL, -- Nome do município
  seq_estado int2 NOT NULL, -- Estado do município
  CONSTRAINT pk_municipio PRIMARY KEY (seq_municipio),
  CONSTRAINT fk_municipio_estado FOREIGN KEY (seq_estado)
      REFERENCES estado (seq_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE municipio OWNER TO admin_asserjuf;
COMMENT ON COLUMN municipio.seq_municipio IS 'chave de identificação do município';
COMMENT ON COLUMN municipio.nom_municipio IS 'Nome do município';
COMMENT ON COLUMN municipio.seq_estado IS 'Estado do município';


-------------------------------------


CREATE TABLE orgao
(
  seq_orgao int2 NOT NULL, -- Chave de identificação do órgão
  nom_orgao varchar(30) NOT NULL, -- Nome descritivo do órgão
  CONSTRAINT pk_orgao PRIMARY KEY (seq_orgao)
) 
WITHOUT OIDS;
ALTER TABLE orgao OWNER TO admin_asserjuf;
COMMENT ON COLUMN orgao.seq_orgao IS 'Chave de identificação do órgão';
COMMENT ON COLUMN orgao.nom_orgao IS 'Nome descritivo do órgão';


-------------------------------------


CREATE TABLE profissao
(
  seq_profissao int2 NOT NULL, -- Chave de identificação de profissão 
  nom_profissao varchar(40) NOT NULL, -- Nome da profissão
  CONSTRAINT pk_profissao PRIMARY KEY (seq_profissao)
) 
WITHOUT OIDS;
ALTER TABLE profissao OWNER TO admin_asserjuf;
COMMENT ON TABLE profissao IS 'Armazena as profissões dos associados da Asserjuf';
COMMENT ON COLUMN profissao.seq_profissao IS 'Chave de identificação de profissão ';
COMMENT ON COLUMN profissao.nom_profissao IS 'Nome da profissão';


-------------------------------------------


CREATE TABLE setor
(
  seq_setor int2 NOT NULL, -- Chave de identificação do setor
  nom_setor varchar(30) NOT NULL, -- Nome do setor.
  des_endereco_setor varchar(100), -- Endereço do setor
  seq_municipio int2, -- Chave de identificação do município do setor.
  num_telefone_setor int8, -- Número do telefone do setor.
  seq_orgao int2 NOT NULL, -- chave de identificação do órgão ao qual o setor faz parte
  CONSTRAINT pk_setor PRIMARY KEY (seq_setor),
  CONSTRAINT fk_setor_municipio FOREIGN KEY (seq_setor)
      REFERENCES municipio (seq_municipio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_setor_orgao FOREIGN KEY (seq_orgao)
      REFERENCES orgao (seq_orgao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE setor OWNER TO admin_asserjuf;
COMMENT ON TABLE setor IS 'Armazena os setores da Justiça Federal';
COMMENT ON COLUMN setor.seq_setor IS 'Chave de identificação do setor';
COMMENT ON COLUMN setor.nom_setor IS 'Nome do setor.';
COMMENT ON COLUMN setor.des_endereco_setor IS 'Endereço do setor';
COMMENT ON COLUMN setor.seq_municipio IS 'Chave de identificação do município do setor.';
COMMENT ON COLUMN setor.num_telefone_setor IS 'Número do telefone do setor.';
COMMENT ON COLUMN setor.seq_orgao IS 'chave de identificação do órgão ao qual o setor faz parte';



------------------------------------


CREATE TABLE tipo_evento
(
  seq_tipo_evento int2 NOT NULL, -- Chave de identificação do evento.
  nom_tipo_evento varchar(25) NOT NULL, -- Nome do tipo do evento.
  CONSTRAINT pk_tipo_evento PRIMARY KEY (seq_tipo_evento)
) 
WITHOUT OIDS;
ALTER TABLE tipo_evento OWNER TO admin_asserjuf;
COMMENT ON TABLE tipo_evento IS 'Eventos de cadastro dos associados (cadastro, cancelamento e reativação).';
COMMENT ON COLUMN tipo_evento.seq_tipo_evento IS 'Chave de identificação do evento.';
COMMENT ON COLUMN tipo_evento.nom_tipo_evento IS 'Nome do tipo do evento.';


------------------------------------


CREATE TABLE valor_mensalidade
(
  seq_valor_mensalidade int2 NOT NULL, -- Chave de identificação do valor da mensalidade
  dat_cadastro_valor_mensalidade date DEFAULT ('now'::text)::date, -- Data da inclusão do valor mensalidade
  val_valor_mensalidade numeric(14,2), -- Valor da mensalidade
  CONSTRAINT pk_valor_mensalidade PRIMARY KEY (seq_valor_mensalidade)
) 
WITHOUT OIDS;
ALTER TABLE valor_mensalidade OWNER TO admin_asserjuf;
COMMENT ON TABLE valor_mensalidade IS 'Armazena o valor da mensalidade de sócio usuário';
COMMENT ON COLUMN valor_mensalidade.seq_valor_mensalidade IS 'Chave de identificação do valor da mensalidade';
COMMENT ON COLUMN valor_mensalidade.dat_cadastro_valor_mensalidade IS 'Data da inclusão do valor mensalidade';
COMMENT ON COLUMN valor_mensalidade.val_valor_mensalidade IS 'Valor da mensalidade';


-----------------------------------

CREATE TABLE associado
(
  seq_pessoa int4 NOT NULL, -- Chave de identificação de pessoa, nesse caso vai foncionar também como matrícula dos associados na ASSERJUF.
  des_logradouro_associado varchar(80), -- Logradouro do associado
  des_numero_endereco_associado varchar(6), -- Número do endereço do associado.
  des_complemento_endereco_associado varchar(20),
  des_bairro_associado varchar(40),
  num_cep_associado int4,
  des_municipio_endereco_associado varchar(50),
  seq_estado_endereco_associado int2,
  num_telefone_residencial_associado int8,
  num_telefone_comercial_associado int8,
  num_telefone_celular_associado int8,
  des_email_associado varchar(50),
  des_naturalidade_associado varchar(50),
  seq_estado_naturalidade_associado int2, -- Estado de naturalidade do associado.
  sts_estado_civil_associado char(1),
  des_profissao_associado varchar(30), -- profissão do associado.
  des_grupo_sanguineo_associado char(2), -- Grupo sanguineo do associado.
  sts_fator_rh_associado char(1), -- fator RH do associado.
  nom_pai_associado varchar(50), -- Nome do pai do associado.
  nom_mae_associado varchar(30), -- Nome da mãe do associado.
  nom_conjuge_associado varchar(50), -- Nome do cônjuge do associado.
  num_calcado_associado int2,
  sts_situacao_justica_associado char(1),
  nom_orgao_associado varchar(30),
  des_endereco_orgao_associado varchar(100),
  nom_setor_associado varchar(30),
  seq_estado_setor_associado int2,
  nom_municipio_setor_associado varchar(50),
  num_telefone_setor_associado int8,
  num_ramal_setor_associado int2,
  num_matricula_justica_associado int4,
  sts_recebe_jornal_associado char(1),
  sts_categoria_associado char(1),
  seq_contribuinte_associado int4,
  seq_banco int2,
  num_agencia_associado char(4),
  dig_agencia_associado char(1),
  num_conta_associado varchar(10),
  dig_conta_associado char(1),
  CONSTRAINT pk_associado PRIMARY KEY (seq_pessoa),
  CONSTRAINT fk_associado_associado_contribuinte FOREIGN KEY (seq_contribuinte_associado)
      REFERENCES associado (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_associado_banco FOREIGN KEY (seq_banco)
      REFERENCES banco (seq_banco) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_associado_estado_endereco FOREIGN KEY (seq_estado_endereco_associado)
      REFERENCES estado (seq_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_associado_estado_naturalidade FOREIGN KEY (seq_estado_naturalidade_associado)
      REFERENCES estado (seq_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_associado_estado_setor FOREIGN KEY (seq_estado_setor_associado)
      REFERENCES estado (seq_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE associado OWNER TO admin_asserjuf;
COMMENT ON TABLE associado IS 'Associados da ASSERJUF.';
COMMENT ON COLUMN associado.seq_pessoa IS 'Chave de identificação de pessoa, nesse caso vai foncionar também como matrícula dos associados na ASSERJUF.';
COMMENT ON COLUMN associado.des_logradouro_associado IS 'Logradouro do associado';
COMMENT ON COLUMN associado.des_numero_endereco_associado IS 'Número do endereço do associado.';
COMMENT ON COLUMN associado.seq_estado_naturalidade_associado IS 'Estado de naturalidade do associado.';
COMMENT ON COLUMN associado.des_profissao_associado IS 'profissão do associado.';
COMMENT ON COLUMN associado.des_grupo_sanguineo_associado IS 'Grupo sanguineo do associado.';
COMMENT ON COLUMN associado.sts_fator_rh_associado IS 'fator RH do associado.';
COMMENT ON COLUMN associado.nom_pai_associado IS 'Nome do pai do associado.';
COMMENT ON COLUMN associado.nom_mae_associado IS 'Nome da mãe do associado.';
COMMENT ON COLUMN associado.nom_conjuge_associado IS 'Nome do cônjuge do associado.';


-------------------------------------


CREATE TABLE historico_evento_associado
(
  seq_associado int2 NOT NULL, -- Chave de identificação do associado.
  seq_tipo_evento varchar(30) NOT NULL, -- Chave de identificação do tipo de evento.
  dat_historico_evento_associado varchar(100) NOT NULL, -- Data do histórico de evendo do associado.
  CONSTRAINT pk_historico_evento_associado PRIMARY KEY (seq_associado, seq_tipo_evento, dat_historico_evento_associado),
  CONSTRAINT fk_historico_evento_associado_associado FOREIGN KEY (seq_associado)
      REFERENCES associado (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE historico_evento_associado OWNER TO admin_asserjuf;
COMMENT ON TABLE historico_evento_associado IS 'Histórico de eventos de cadastro dos associados.';
COMMENT ON COLUMN historico_evento_associado.seq_associado IS 'Chave de identificação do associado.';
COMMENT ON COLUMN historico_evento_associado.seq_tipo_evento IS 'Chave de identificação do tipo de evento.';
COMMENT ON COLUMN historico_evento_associado.dat_historico_evento_associado IS 'Data do histórico de evendo do associado.';



------------------------------------

DROP TABLE historico_evento_associado;

CREATE TABLE historico_evento_associado
(
  seq_associado int4 NOT NULL, -- Chave de identificação do associado.
  seq_tipo_evento int2 NOT NULL, -- Chave de identificação do tipo de evento.
  dat_historico_evento_associado varchar(100) NOT NULL, -- Data do histórico de evendo do associado.
  CONSTRAINT pk_historico_evento_associado PRIMARY KEY (seq_associado, seq_tipo_evento, dat_historico_evento_associado),
  CONSTRAINT fk_historico_evento_associado_associado FOREIGN KEY (seq_associado)
      REFERENCES associado (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE historico_evento_associado OWNER TO admin_asserjuf;
COMMENT ON TABLE historico_evento_associado IS 'Histórico de eventos de cadastro dos associados.';
COMMENT ON COLUMN historico_evento_associado.seq_associado IS 'Chave de identificação do associado.';
COMMENT ON COLUMN historico_evento_associado.seq_tipo_evento IS 'Chave de identificação do tipo de evento.';
COMMENT ON COLUMN historico_evento_associado.dat_historico_evento_associado IS 'Data do histórico de evendo do associado.';



---------------------

create view vw_associado as
select p.seq_pessoa as seq_associado, p.nom_pessoa as nom_associado, p.dat_nascimento_pessoa as dat_nascimento_associado,
	p.sts_sexo_pessoa as sts_sexo_associado, a.des_logradouro_associado, a.des_numero_endereco_associado,
	a.des_complemento_endereco_associado, a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado,
	a.seq_estado_endereco_associado, a.num_telefone_residencial_associado, a.num_telefone_comercial_associado,
	a.num_telefone_celular_associado, a.des_email_associado, a.des_naturalidade_associado, a.seq_estado_naturalidade_associado,
	a.sts_estado_civil_associado, a.des_profissao_associado, a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado,
	a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, a.num_calcado_associado, a.sts_situacao_justica_associado,
	a.nom_orgao_associado, a.des_endereco_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, 
	a.nom_municipio_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, 
	a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado,
	a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado
from pessoa p 
	natural inner join associado a;


---------------------------


INSERT INTO PARAMETROS VALUES ('NUM_DIAS_PROX_ANIVER', '20');



CREATE OR REPLACE FUNCTION proximo_aniversario(date)
  RETURNS date AS	
$BODY$
DECLARE
	data ALIAS FOR $1;

BEGIN

RETURN

	(case 
	When date(extract(Year from CURRENT_DATE)||extract(month from data)||extract(day from data)) < CURRENT_DATE Then (extract(Year from CURRENT_DATE)+1)||extract(month from data)||extract(day from data)
	Else extract(Year from CURRENT_DATE)||extract(month from data)||extract(day from data)
	end) as retorno ;


END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION proximo_aniversario(date) OWNER TO admin_asserjuf;

---------------

alter table valor_mensalidade
drop column val_valor_mensalidade;

alter table valor_mensalidade
add column val_mensalidade numeric(14,2) not null;

COMMENT ON COLUMN valor_mensalidade.val_mensalidade IS 'Valor da mensalidade';

----------------

CREATE SEQUENCE seq_valor_mensalidade
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 32767
  START 7
  CACHE 1;
ALTER TABLE seq_valor_mensalidade OWNER TO postgres;

-----------------

alter table associado
add column dat_exclusao_associado date;

------------------

DROP VIEW vw_associado;

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, p.sts_sexo_pessoa AS sts_sexo_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.des_endereco_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, a.nom_municipio_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado
   FROM pessoa p
NATURAL JOIN associado a;

ALTER TABLE vw_associado OWNER TO postgres;

--------------------

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

-----------------------

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


----------------------

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

----------------------

DROP VIEW vw_dependente;

CREATE OR REPLACE VIEW vw_dependente AS 
 SELECT p.seq_pessoa AS seq_dependente, p.nom_pessoa AS nom_dependente, p.dat_nascimento_pessoa AS dat_nascimento_dependente, p.sts_sexo_pessoa AS sts_sexo_dependente, d.num_rg_dependente, d.des_parentesco_dependente, d.seq_associado
   FROM pessoa p
NATURAL JOIN dependente d;

ALTER TABLE vw_dependente OWNER TO admin_asserjuf;

DROP VIEW vw_filho;

CREATE OR REPLACE VIEW vw_filho AS 
 SELECT p.seq_pessoa AS seq_filho, p.nom_pessoa AS nom_filho, p.dat_nascimento_pessoa AS dat_nascimento_filho, p.sts_sexo_pessoa AS sts_sexo_filho, f.seq_associado
   FROM pessoa p
NATURAL JOIN filho f;

ALTER TABLE vw_filho OWNER TO admin_asserjuf;

---------------------

alter table associado
add sts_pre_cadastro_associado char(1) not null default 'N';

---------------------

delete from pessoa where seq_pessoa in (select seq_associado from vw_associado);
delete from historico_evento_associado;
delete from associado;

alter table associado
add num_cpf_associado int8;

DROP VIEW vw_associado;

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, 
	p.sts_sexo_pessoa AS sts_sexo_associado, a.num_cpf_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, 
	a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, 
	a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, 
	a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, 
	a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, 
	a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, 
	a.nom_municipio_setor_associado, a.des_endereco_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, 
	a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, 
	a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado
   FROM pessoa p
NATURAL JOIN associado a;

---------------------------------




INSERT INTO PARAMETROS VALUES ('TP_EVT_CADASTRO', '1');

INSERT INTO PARAMETROS VALUES ('TP_EVT_CANCELAMENTO', '2');


--------------------------

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

-------------------------

CREATE TABLE LANCAMENTO_ASSOCIADO (
	SEQ_ASSOCIADO	INT4	NOT NULL,
	SEQ_LANCAMENTO	INT8	NOT NULL,	
	CONSTRAINT PK_LANCAMENTO_ASSOCIADO PRIMARY KEY (SEQ_ASSOCIADO, SEQ_LANCAMENTO),
	CONSTRAINT FK_LANCAMENTO_ASSOCIADO_ASSOCIADO FOREIGN KEY (SEQ_ASSOCIADO) REFERENCES ASSOCIADO(SEQ_PESSOA),
	CONSTRAINT FK_LANCAMENTO_ASSOCIADO_LANCAMENTO FOREIGN KEY (SEQ_LANCAMENTO) REFERENCES LANCAMENTO(SEQ_LANCAMENTO)
);

--------------------------------

CREATE OR REPLACE FUNCTION filtro_val_mensalidade(real, real, int4)
  RETURNS boolean AS
$BODY$
DECLARE

	p_valor_menor ALIAS FOR $1;
	p_valor_maior ALIAS FOR $2;
	seq_associado ALIAS FOR $3;


	valor_menor real;
	valor_maior real;
BEGIN

	if p_valor_menor is null Then
		valor_menor = 0;
	end if;	

	if p_valor_maior is null Then
		valor_maior = 1000000;
	end if;	

	return exists(select l.val_lancamento 
			from lancamento_associado la
			natural join lancamento l
			natural join baixa_lancamento bl
			where la.seq_associado = a.seq_associado
			and l.val_lancamnto between valor_menor and valor_maior	);

END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION filtro_val_mensalidade(real, real, int4) OWNER TO admin_asserjuf;


----------------------

alter table associado
add num_rg_associado int8 not null;

DROP VIEW vw_associado;

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, 
	p.sts_sexo_pessoa AS sts_sexo_associado, a.num_rg_associado, a.num_cpf_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, 
	a.des_complemento_endereco_associado, a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, 
	a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, 
	a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, 
	a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, 
	a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, 
	a.nom_municipio_setor_associado, a.des_endereco_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, 
	a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, 
	a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado
   FROM pessoa p
NATURAL JOIN associado a;

ALTER TABLE vw_associado OWNER TO admin_asserjuf;


---------------------

CREATE OR REPLACE FUNCTION remove_pessoa()
RETURNS trigger AS
$BODY$
DECLARE

BEGIN

	delete from pessoa where seq_pessoa = old.seq_pessoa;
	
	return new;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION remove_pessoa() OWNER TO admin_asserjuf;

----------------------------

CREATE TRIGGER tg_remove_filho AFTER DELETE
    ON filho FOR EACH ROW
    EXECUTE PROCEDURE remove_pessoa (  );

----------------------------

CREATE TRIGGER tg_remove_dependente AFTER DELETE
    ON dependente FOR EACH ROW
    EXECUTE PROCEDURE remove_pessoa (  );

----------------------------


-- Function: proximo_aniversario(date)

-- DROP FUNCTION proximo_aniversario(date);

CREATE OR REPLACE FUNCTION proximo_aniversario(date)
  RETURNS date AS
$BODY$
DECLARE
	data ALIAS FOR $1;

BEGIN

RETURN

	(case 
	When date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data)) < CURRENT_DATE Then date((extract(Year from CURRENT_DATE)+1)||'-'||extract(month from data)||'-'||extract(day from data))
	Else date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data))
	end) as retorno ;

--	select (case 
--	When date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data)) < CURRENT_DATE Then date((extract(Year from CURRENT_DATE)+1)||'-'||extract(month from data)||'-'||extract(day from data))
--	Else date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data))
--	end) as retorno ;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION proximo_aniversario(date) OWNER TO admin_asserjuf;


----------------

INSERT INTO ESTADO VALUES (14, 'ACRE', 'AC');
INSERT INTO ESTADO VALUES (3, 'ALAGOAS', 'AL');
INSERT INTO ESTADO VALUES (10, 'AMAPÁ', 'AP');
INSERT INTO ESTADO VALUES (11, 'AMAZONAS', 'AM');
INSERT INTO ESTADO VALUES (1, 'BAHIA', 'BA');
INSERT INTO ESTADO VALUES (8, 'CEARÁ', 'CE');
INSERT INTO ESTADO VALUES (27, 'DISTRITO FEDERAL', 'DF');
INSERT INTO ESTADO VALUES (21, 'ESPÍRITO SANTO', 'ES');
INSERT INTO ESTADO VALUES (17, 'GOIÁS', 'GO');
INSERT INTO ESTADO VALUES (9, 'MARANHÃO', 'MA');
INSERT INTO ESTADO VALUES (18, 'MATO GROSSO', 'MT');
INSERT INTO ESTADO VALUES (19, 'MATO GROSSO DO SUL', 'MS');
INSERT INTO ESTADO VALUES (20, 'MINAS GERAIS', 'MG');
INSERT INTO ESTADO VALUES (16, 'PARÁ', 'PA');
INSERT INTO ESTADO VALUES (5, 'PARAÍBA', 'PB');
INSERT INTO ESTADO VALUES (25, 'PARANÁ', 'PR');
INSERT INTO ESTADO VALUES (4, 'PERNAMBUCO', 'PE');
INSERT INTO ESTADO VALUES (7, 'PIAUÍ', 'PI');
INSERT INTO ESTADO VALUES (22, 'RIO DE JANEIRO', 'RJ');
INSERT INTO ESTADO VALUES (6, 'RIO GRANDE DO NORTE', 'RN');
INSERT INTO ESTADO VALUES (26, 'RIO GRANDE DO SUL', 'RS');
INSERT INTO ESTADO VALUES (13, 'RONDÔNIA', 'RO');
INSERT INTO ESTADO VALUES (12, 'RORAIMA', 'RR');
INSERT INTO ESTADO VALUES (24, 'SANTA CATARINA', 'SC');
INSERT INTO ESTADO VALUES (23, 'SÃO PAULO', 'SP');
INSERT INTO ESTADO VALUES (2, 'SERGIPE', 'SE');
INSERT INTO ESTADO VALUES (15, 'TOCANTINS', 'TO');



-------------------------

DROP VIEW vw_associado;

alter table associado
alter column des_profissao_associado type varchar(50);

alter table associado
alter column nom_mae_associado type varchar(50);

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, p.sts_sexo_pessoa AS sts_sexo_associado, a.num_rg_associado, a.num_cpf_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, a.nom_municipio_setor_associado, a.des_endereco_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado
   FROM pessoa p
NATURAL JOIN associado a;

ALTER TABLE vw_associado OWNER TO admin_asserjuf;

-------------------------

CREATE OR REPLACE FUNCTION filtro_val_mensalidade(float4, float4, int4)
  RETURNS bool AS
$BODY$
DECLARE

	p_valor_menor ALIAS FOR $1;
	p_valor_maior ALIAS FOR $2;
	seq_associado ALIAS FOR $3;


	valor_menor float4;
	valor_maior float4;
BEGIN

	if p_valor_menor is null Then
		valor_menor = 0;
	else
		valor_menor = p_valor_menor;
	end if;	

	if p_valor_maior is null Then
		valor_maior = 1000000;
	else
		valor_maior = p_valor_maior;
	end if;	

	return exists(select l.val_lancamento 
			from lancamento_associado la
			natural join lancamento l
			natural join baixa_lancamento bl
			where la.seq_associado = seq_associado
			and l.val_lancamento between valor_menor and valor_maior);

END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION filtro_val_mensalidade(float4, float4, int4) OWNER TO admin_asserjuf;

--------------------------



delete from historico_evento_associado;

DELETE FROM DEPENDENTE;

DELETE FROM FILHO;

delete from associado ;

delete from pessoa;




DELETE FROM PARENTESCO;

INSERT INTO PARENTESCO VALUES (1, 'FILHO(A)');
INSERT INTO PARENTESCO VALUES (2, 'IRMÃO(Ã)');
INSERT INTO PARENTESCO VALUES (3, 'PRIMO(A)');
INSERT INTO PARENTESCO VALUES (4, 'TIO(A)');
INSERT INTO PARENTESCO VALUES (5, 'AVÔ(Ó)');
INSERT INTO PARENTESCO VALUES (6, 'CUNHADO(A)');
INSERT INTO PARENTESCO VALUES (7, 'MARIDO');
INSERT INTO PARENTESCO VALUES (8, 'ESPOSA');
INSERT INTO PARENTESCO VALUES (9, 'ENTEADO(A)');
INSERT INTO PARENTESCO VALUES (10, 'NORA');
INSERT INTO PARENTESCO VALUES (11, 'GENRO');
INSERT INTO PARENTESCO VALUES (12, 'SOGRO(A)');
INSERT INTO PARENTESCO VALUES (13, 'PAI');
INSERT INTO PARENTESCO VALUES (14, 'MÃE');

--------------------------------


INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('SMTP_SERVER','mail.asserjuf.org.br');

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('SMTP_USER_ACCOUNT','sisjuf');

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('SMTP_USER_PWD','paulo321');

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('EMAIL_SISJUF','sisjuf@asserjuf.org.br');

--------------------------------

-- Function: obter_forma_pagamento(int8)

-- DROP FUNCTION obter_forma_pagamento(int8);

CREATE OR REPLACE FUNCTION obter_forma_pagamento(int8)
  RETURNS "varchar" AS
$BODY$
DECLARE
	id_lancamento ALIAS FOR $1;

	retorno varchar(512);
	registro RECORD;
	id_cheque int2;
BEGIN
	retorno := '';

	select into id_cheque cast(str_val_parametro as int2) from parametros where nom_parametro = 'FORMA_PAGTO_CHEQUE';	

	for registro in 
		select fp.nom_forma_pagamento, bl.seq_forma_pagamento, bl.des_banco_cheque_baixa_lancamento, 
		bl.num_agencia_cheque_baixa_lancamento,	bl.dig_agencia_cheque_baixa_lancamento, 
		bl.num_conta_cheque_baixa_lancamento, bl.dig_conta_cheque_baixa_lancamento, bl.num_cheque_baixa_lancamento
		from baixa_lancamento bl natural inner join forma_pagamento fp
		where bl.seq_lancamento = id_lancamento
		order by bl.dat_baixa_lancamento
	LOOP	

		retorno := retorno || registro.nom_forma_pagamento; 
		
		IF registro.seq_forma_pagamento = id_cheque THEN
			retorno := retorno || ' Nº' || registro.num_cheque_baixa_lancamento || ' ';
			retorno := retorno || registro.des_banco_cheque_baixa_lancamento || ' ' || registro.num_agencia_cheque_baixa_lancamento;
			IF (registro.dig_agencia_cheque_baixa_lancamento is not null) AND (registro.dig_agencia_cheque_baixa_lancamento <> '') THEN
				retorno := retorno || '-' || registro.dig_agencia_cheque_baixa_lancamento;
			END IF;
			retorno := retorno || '/' || registro.num_conta_cheque_baixa_lancamento || '-' || registro.dig_conta_cheque_baixa_lancamento;
		END IF;
		
		retorno := retorno || ', '; 

	END LOOP;

	IF retorno <> '' THEN
		retorno := substring(retorno from 1 for (char_length(retorno)-2) );
	ELSE
		retorno := null;
	END IF;
	
	RETURN retorno;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION obter_forma_pagamento(int8) OWNER TO admin_asserjuf;

----------------------------------

DELETE FROM PARENTESCO;

INSERT INTO PARENTESCO VALUES (1,  'FILHO(A)');

INSERT INTO PARENTESCO VALUES (2,  'IRMÃO(Ã)');

INSERT INTO PARENTESCO VALUES (3,  'PRIMO(A)');

INSERT INTO PARENTESCO VALUES (4,  'TIO(A)');

INSERT INTO PARENTESCO VALUES (5,  'AVÔ(Ó)');

INSERT INTO PARENTESCO VALUES (6,  'MARIDO');

INSERT INTO PARENTESCO VALUES (7,  'ESPOSA');

INSERT INTO PARENTESCO VALUES (8,  'ENTEADO(A)');

INSERT INTO PARENTESCO VALUES (9,  'NETO(A)');

INSERT INTO PARENTESCO VALUES (10, 'PAI');

INSERT INTO PARENTESCO VALUES (11, 'MÃE');

-----------------------


INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('CONTA_CREDITO_MENSALIDADE', 1);
INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('CONTA_DEBITO_CUSTO_CONSIG', 1);

INSERT INTO ORIGEM_LANCAMENTO (SEQ_ORIGEM_LANCAMENTO, NOM_ORIGEM_LANCAMENTO, FLG_ATIVO_ORIGEM_LANCAMENTO, FLG_NAO_APAGAVEL_ORIGEM_LANCAMENTO)
VALUES ((SELECT NEXTVAL('SEQ_ORIGEM_LANCAMENTO')), 'ASSOCIADOS', B'0'::"bit", B'1'::"bit");

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO) VALUES ('ORIGEM_ASSOCIADOS', (SELECT SEQ_ORIGEM_LANCAMENTO FROM ORIGEM_LANCAMENTO WHERE NOM_ORIGEM_LANCAMENTO = 'ASSOCIADOS'));


---------------------------

DELETE FROM PARAMETROS WHERE NOM_PARAMETRO = 'CONTA_DEBITO_CUSTO_CONSIG';

UPDATE PARAMETROS SET NOM_PARAMETRO = 'CONTA_CONSIGNACAO_NUCRE' WHERE NOM_PARAMETRO = 'CONTA_CREDITO_MENSALIDADE';

CREATE UNIQUE INDEX "IDX_MATRICULA_JUSTICA"
  ON associado
  USING btree
  (num_matricula_justica_associado);

----------------------------



INSERT INTO PARAMETROS  (NOM_PARAMETRO, STR_VAL_PARAMETRO)
VALUES ('FORMA_PAGTO_DEBITO_AUT','3');

------------

delete from parametros where nom_parametro  = 'CONTA_CONSIGNACAO_NUCRE';

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO)
VALUES ('CONTA_DEBITO_CUSTO_CONSIG', 1);

INSERT INTO PARAMETROS (NOM_PARAMETRO, STR_VAL_PARAMETRO)
VALUES ('CONTA_CREDITO_MENSALIDADE', 1);

------------

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

----------------------------

DROP VIEW vw_associado;

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, p.sts_sexo_pessoa AS sts_sexo_associado, a.num_rg_associado, a.num_cpf_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, a.nom_municipio_setor_associado, a.des_endereco_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado, a.sts_pre_cadastro_associado, t.seq_tipo_evento, t.nom_tipo_evento, h.dat_historico_evento_associado
   FROM pessoa p
NATURAL JOIN associado a
   JOIN historico_evento_associado h ON a.seq_pessoa = h.seq_associado
NATURAL JOIN tipo_evento t
  WHERE h.seq_historico_evento_associado = (( SELECT max(h2.seq_historico_evento_associado) AS max
   FROM historico_evento_associado h2
  WHERE h2.seq_associado = a.seq_pessoa));

ALTER TABLE vw_associado OWNER TO admin_asserjuf;

-----------------------------


ALTER TABLE historico_evento_associado DROP CONSTRAINT pk_historico_evento_associado;

ALTER TABLE historico_evento_associado
  ADD CONSTRAINT pk_historico_evento_associado PRIMARY KEY(seq_historico_evento_associado);

-------------------------------

DROP VIEW vw_associado;

CREATE OR REPLACE VIEW vw_associado AS 
 SELECT p.seq_pessoa AS seq_associado, p.nom_pessoa AS nom_associado, p.dat_nascimento_pessoa AS dat_nascimento_associado, p.sts_sexo_pessoa AS sts_sexo_associado, a.num_rg_associado, a.num_cpf_associado, a.des_logradouro_associado, a.des_numero_endereco_associado, a.des_complemento_endereco_associado, a.des_bairro_associado, a.num_cep_associado, a.des_municipio_endereco_associado, a.seq_estado_endereco_associado, a.num_telefone_residencial_associado, a.num_telefone_comercial_associado, a.num_telefone_celular_associado, a.des_email_associado, a.des_naturalidade_associado, a.seq_estado_naturalidade_associado, a.sts_estado_civil_associado, a.des_profissao_associado, a.des_grupo_sanguineo_associado, a.sts_fator_rh_associado, a.nom_pai_associado, a.nom_mae_associado, a.nom_conjuge_associado, a.num_calcado_associado, a.sts_situacao_justica_associado, a.nom_orgao_associado, a.nom_setor_associado, a.seq_estado_setor_associado, a.nom_municipio_setor_associado, a.des_endereco_setor_associado, a.num_telefone_setor_associado, a.num_ramal_setor_associado, a.num_matricula_justica_associado, a.sts_recebe_jornal_associado, a.sts_categoria_associado, a.seq_contribuinte_associado, a.seq_banco, a.num_agencia_associado, a.dig_agencia_associado, a.num_conta_associado, a.dig_conta_associado, a.dat_exclusao_associado, a.sts_pre_cadastro_associado, t.seq_tipo_evento, t.nom_tipo_evento, h.dat_historico_evento_associado
   FROM pessoa p
NATURAL JOIN associado a
   JOIN historico_evento_associado h ON a.seq_pessoa = h.seq_associado
NATURAL JOIN tipo_evento t
  WHERE h.seq_historico_evento_associado = (( SELECT max(h2.seq_historico_evento_associado) AS max
   FROM historico_evento_associado h2
  WHERE h2.seq_associado = a.seq_pessoa));

ALTER TABLE vw_associado OWNER TO admin_asserjuf;

---------------------------------