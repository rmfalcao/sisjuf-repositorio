CREATE TABLE pessoa
(
  seq_pessoa int4 NOT NULL, -- Chave de identifica��o de pessoa.
  nom_pessoa varchar(60) NOT NULL, -- Nome da pessoa.
  dat_nascimento_pessoa date NOT NULL, -- Data de nascimento.
  sts_sexo_pessoa char(1) NOT NULL, -- Sexo.
  CONSTRAINT pk_pessoa PRIMARY KEY (seq_pessoa)
) 
WITHOUT OIDS;
ALTER TABLE pessoa OWNER TO admin_asserjuf;
COMMENT ON COLUMN pessoa.seq_pessoa IS 'Chave de identifica��o de pessoa.';
COMMENT ON COLUMN pessoa.nom_pessoa IS 'Nome da pessoa.';
COMMENT ON COLUMN pessoa.dat_nascimento_pessoa IS 'Data de nascimento.';
COMMENT ON COLUMN pessoa.sts_sexo_pessoa IS 'Sexo.';



---------------------------------------


CREATE TABLE dependente
(
  seq_pessoa int4 NOT NULL, -- Chave de identifica��o de pessoa.
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
COMMENT ON COLUMN dependente.seq_pessoa IS 'Chave de identifica��o de pessoa.';
COMMENT ON COLUMN dependente.num_rg_dependente IS 'RG do dependente.';
COMMENT ON COLUMN dependente.des_parentesco_dependente IS 'Parentesco do dependente.';


--------------------------------------

CREATE TABLE estado
(
  seq_estado int2 NOT NULL, -- Chave de identifica��o da tabela estado
  nom_estado varchar(40) NOT NULL, -- Nome do estado.
  sig_estado char(2) NOT NULL, -- Sigla do estado
  CONSTRAINT pk_estado PRIMARY KEY (seq_estado)
) 
WITHOUT OIDS;
ALTER TABLE estado OWNER TO admin_asserjuf;
COMMENT ON TABLE estado IS 'Estados brasileiros';
COMMENT ON COLUMN estado.seq_estado IS 'Chave de identifica��o da tabela estado';
COMMENT ON COLUMN estado.nom_estado IS 'Nome do estado.';
COMMENT ON COLUMN estado.sig_estado IS 'Sigla do estado';




--------------------------------------



CREATE TABLE filho
(
  seq_pessoa int4 NOT NULL, -- Chave de identifica��o de pessoa.
  CONSTRAINT pk_filho PRIMARY KEY (seq_pessoa),
  CONSTRAINT fk_filho_pessoa FOREIGN KEY (seq_pessoa)
      REFERENCES pessoa (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE filho OWNER TO admin_asserjuf;
COMMENT ON TABLE filho IS 'filhos dos associados da Asserjuf.';
COMMENT ON COLUMN filho.seq_pessoa IS 'Chave de identifica��o de pessoa.';




-------------------------------------


CREATE TABLE municipio
(
  seq_municipio int2 NOT NULL, -- chave de identifica��o do munic�pio
  nom_municipio varchar(50) NOT NULL, -- Nome do munic�pio
  seq_estado int2 NOT NULL, -- Estado do munic�pio
  CONSTRAINT pk_municipio PRIMARY KEY (seq_municipio),
  CONSTRAINT fk_municipio_estado FOREIGN KEY (seq_estado)
      REFERENCES estado (seq_estado) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE municipio OWNER TO admin_asserjuf;
COMMENT ON COLUMN municipio.seq_municipio IS 'chave de identifica��o do munic�pio';
COMMENT ON COLUMN municipio.nom_municipio IS 'Nome do munic�pio';
COMMENT ON COLUMN municipio.seq_estado IS 'Estado do munic�pio';


-------------------------------------


CREATE TABLE orgao
(
  seq_orgao int2 NOT NULL, -- Chave de identifica��o do �rg�o
  nom_orgao varchar(30) NOT NULL, -- Nome descritivo do �rg�o
  CONSTRAINT pk_orgao PRIMARY KEY (seq_orgao)
) 
WITHOUT OIDS;
ALTER TABLE orgao OWNER TO admin_asserjuf;
COMMENT ON COLUMN orgao.seq_orgao IS 'Chave de identifica��o do �rg�o';
COMMENT ON COLUMN orgao.nom_orgao IS 'Nome descritivo do �rg�o';


-------------------------------------


CREATE TABLE profissao
(
  seq_profissao int2 NOT NULL, -- Chave de identifica��o de profiss�o 
  nom_profissao varchar(40) NOT NULL, -- Nome da profiss�o
  CONSTRAINT pk_profissao PRIMARY KEY (seq_profissao)
) 
WITHOUT OIDS;
ALTER TABLE profissao OWNER TO admin_asserjuf;
COMMENT ON TABLE profissao IS 'Armazena as profiss�es dos associados da Asserjuf';
COMMENT ON COLUMN profissao.seq_profissao IS 'Chave de identifica��o de profiss�o ';
COMMENT ON COLUMN profissao.nom_profissao IS 'Nome da profiss�o';


-------------------------------------------


CREATE TABLE setor
(
  seq_setor int2 NOT NULL, -- Chave de identifica��o do setor
  nom_setor varchar(30) NOT NULL, -- Nome do setor.
  des_endereco_setor varchar(100), -- Endere�o do setor
  seq_municipio int2, -- Chave de identifica��o do munic�pio do setor.
  num_telefone_setor int8, -- N�mero do telefone do setor.
  seq_orgao int2 NOT NULL, -- chave de identifica��o do �rg�o ao qual o setor faz parte
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
COMMENT ON TABLE setor IS 'Armazena os setores da Justi�a Federal';
COMMENT ON COLUMN setor.seq_setor IS 'Chave de identifica��o do setor';
COMMENT ON COLUMN setor.nom_setor IS 'Nome do setor.';
COMMENT ON COLUMN setor.des_endereco_setor IS 'Endere�o do setor';
COMMENT ON COLUMN setor.seq_municipio IS 'Chave de identifica��o do munic�pio do setor.';
COMMENT ON COLUMN setor.num_telefone_setor IS 'N�mero do telefone do setor.';
COMMENT ON COLUMN setor.seq_orgao IS 'chave de identifica��o do �rg�o ao qual o setor faz parte';



------------------------------------


CREATE TABLE tipo_evento
(
  seq_tipo_evento int2 NOT NULL, -- Chave de identifica��o do evento.
  nom_tipo_evento varchar(25) NOT NULL, -- Nome do tipo do evento.
  CONSTRAINT pk_tipo_evento PRIMARY KEY (seq_tipo_evento)
) 
WITHOUT OIDS;
ALTER TABLE tipo_evento OWNER TO admin_asserjuf;
COMMENT ON TABLE tipo_evento IS 'Eventos de cadastro dos associados (cadastro, cancelamento e reativa��o).';
COMMENT ON COLUMN tipo_evento.seq_tipo_evento IS 'Chave de identifica��o do evento.';
COMMENT ON COLUMN tipo_evento.nom_tipo_evento IS 'Nome do tipo do evento.';


------------------------------------


CREATE TABLE valor_mensalidade
(
  seq_valor_mensalidade int2 NOT NULL, -- Chave de identifica��o do valor da mensalidade
  dat_cadastro_valor_mensalidade date DEFAULT ('now'::text)::date, -- Data da inclus�o do valor mensalidade
  val_valor_mensalidade numeric(14,2), -- Valor da mensalidade
  CONSTRAINT pk_valor_mensalidade PRIMARY KEY (seq_valor_mensalidade)
) 
WITHOUT OIDS;
ALTER TABLE valor_mensalidade OWNER TO admin_asserjuf;
COMMENT ON TABLE valor_mensalidade IS 'Armazena o valor da mensalidade de s�cio usu�rio';
COMMENT ON COLUMN valor_mensalidade.seq_valor_mensalidade IS 'Chave de identifica��o do valor da mensalidade';
COMMENT ON COLUMN valor_mensalidade.dat_cadastro_valor_mensalidade IS 'Data da inclus�o do valor mensalidade';
COMMENT ON COLUMN valor_mensalidade.val_valor_mensalidade IS 'Valor da mensalidade';


-----------------------------------

CREATE TABLE associado
(
  seq_pessoa int4 NOT NULL, -- Chave de identifica��o de pessoa, nesse caso vai foncionar tamb�m como matr�cula dos associados na ASSERJUF.
  des_logradouro_associado varchar(80), -- Logradouro do associado
  des_numero_endereco_associado varchar(6), -- N�mero do endere�o do associado.
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
  des_profissao_associado varchar(30), -- profiss�o do associado.
  des_grupo_sanguineo_associado char(2), -- Grupo sanguineo do associado.
  sts_fator_rh_associado char(1), -- fator RH do associado.
  nom_pai_associado varchar(50), -- Nome do pai do associado.
  nom_mae_associado varchar(30), -- Nome da m�e do associado.
  nom_conjuge_associado varchar(50), -- Nome do c�njuge do associado.
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
COMMENT ON COLUMN associado.seq_pessoa IS 'Chave de identifica��o de pessoa, nesse caso vai foncionar tamb�m como matr�cula dos associados na ASSERJUF.';
COMMENT ON COLUMN associado.des_logradouro_associado IS 'Logradouro do associado';
COMMENT ON COLUMN associado.des_numero_endereco_associado IS 'N�mero do endere�o do associado.';
COMMENT ON COLUMN associado.seq_estado_naturalidade_associado IS 'Estado de naturalidade do associado.';
COMMENT ON COLUMN associado.des_profissao_associado IS 'profiss�o do associado.';
COMMENT ON COLUMN associado.des_grupo_sanguineo_associado IS 'Grupo sanguineo do associado.';
COMMENT ON COLUMN associado.sts_fator_rh_associado IS 'fator RH do associado.';
COMMENT ON COLUMN associado.nom_pai_associado IS 'Nome do pai do associado.';
COMMENT ON COLUMN associado.nom_mae_associado IS 'Nome da m�e do associado.';
COMMENT ON COLUMN associado.nom_conjuge_associado IS 'Nome do c�njuge do associado.';


-------------------------------------


CREATE TABLE historico_evento_associado
(
  seq_associado int2 NOT NULL, -- Chave de identifica��o do associado.
  seq_tipo_evento varchar(30) NOT NULL, -- Chave de identifica��o do tipo de evento.
  dat_historico_evento_associado varchar(100) NOT NULL, -- Data do hist�rico de evendo do associado.
  CONSTRAINT pk_historico_evento_associado PRIMARY KEY (seq_associado, seq_tipo_evento, dat_historico_evento_associado),
  CONSTRAINT fk_historico_evento_associado_associado FOREIGN KEY (seq_associado)
      REFERENCES associado (seq_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITHOUT OIDS;
ALTER TABLE historico_evento_associado OWNER TO admin_asserjuf;
COMMENT ON TABLE historico_evento_associado IS 'Hist�rico de eventos de cadastro dos associados.';
COMMENT ON COLUMN historico_evento_associado.seq_associado IS 'Chave de identifica��o do associado.';
COMMENT ON COLUMN historico_evento_associado.seq_tipo_evento IS 'Chave de identifica��o do tipo de evento.';
COMMENT ON COLUMN historico_evento_associado.dat_historico_evento_associado IS 'Data do hist�rico de evendo do associado.';



------------------------------------

