delete from dependente;

alter table dependente
add seq_associado int4 not null;

alter table dependente 
add constraint fk_dependente_asociado
foreign key (seq_associado)
references associado (seq_pessoa);

delete from filho;
 
alter table filho
add seq_associado int4 not null

alter table filho
add constraint fk_filho_asociado
foreign key (seq_associado)
references associado (seq_pessoa);

