CREATE TABLE DOCUMENTOS_ASSOCIADO (
	SEQ_DOCUMENTO	INTEGER NOT NULL,
	seq_associado	integer not null,
	NOM_DOCUMENTO	character varying(80) not null,
	DAT_DOCUMENTO	TIMESTAMP NULL,
	NOM_ARQUIVO		character varying(100) not null,
	BLOB_ARQUIVO	bytea null,
	DAT_CADASTRO timestamp default now(),
	SEQ_USUARIO_CRIACAO	INTEGER NULL
);

alter table documentos_associado add CONSTRAINT pk_documento_associado PRIMARY KEY (seq_documento);

alter table documentos_associado add CONSTRAINT fk_documento_associado_associado FOREIGN KEY (seq_associado) REFERENCES associado (seq_pessoa);

create sequence SEQ_DOCUMENTO;