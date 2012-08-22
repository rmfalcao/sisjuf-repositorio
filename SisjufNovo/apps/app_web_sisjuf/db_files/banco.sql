-- Table: banco

-- DROP TABLE banco;

CREATE TABLE banco
(
  seq_banco int2 NOT NULL,
  num_banco char(3), -- Número do banco.
  nom_banco varchar(30) NOT NULL, -- Nome do banco.
  sig_banco char(6) NOT NULL, -- Sigla do banco.
  CONSTRAINT pk_banco PRIMARY KEY (seq_banco)
) 
WITHOUT OIDS;
ALTER TABLE banco OWNER TO admin_asserjuf;
COMMENT ON TABLE banco IS 'Bancos da Asserjuf.';
COMMENT ON COLUMN banco.num_banco IS 'Número do banco.';
COMMENT ON COLUMN banco.nom_banco IS 'Nome do banco.';
COMMENT ON COLUMN banco.sig_banco IS 'Sigla do banco.';




