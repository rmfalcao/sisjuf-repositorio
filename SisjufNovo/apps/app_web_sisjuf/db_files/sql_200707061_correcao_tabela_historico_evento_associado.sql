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




