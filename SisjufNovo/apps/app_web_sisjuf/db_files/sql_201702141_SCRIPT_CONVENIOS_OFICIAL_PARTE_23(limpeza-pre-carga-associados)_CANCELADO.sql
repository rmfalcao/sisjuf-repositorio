delete from vinculacao_plano;

delete from outros_beneficiaveis;

delete from dependente;

delete from filho;

delete from conjuge;

delete from historico_evento_associado;

delete from associado;

delete from pessoa;

insert into parentesco values (12, 'AFILHADO(A)');

insert into parentesco values (13, 'AGREGADO(A)');

insert into parentesco values (14, 'COMPADRE/COMADRE');
insert into parentesco values (15, 'COMPANHEIRO(A)');

insert into parentesco values (16, 'CUNHADO(A)');


insert into parentesco values (17, 'GENRO/NORA');


insert into parentesco values (18, 'PADRASTO');

insert into parentesco values (19, 'MADRASTA');


insert into parentesco values (20, 'SOBRINHO(A)');


insert into parentesco values (21, 'SOGRO(A)');


insert into parentesco values (22, 'CÔNJUGE');


update xls_dependentes set parentesco = 12 where parentesco in ('AFILHADA','AFILHADO');


update xls_dependentes set parentesco = 13 where parentesco in ('AGREGADA','AGREGADO');

update xls_dependentes set parentesco = 5 where parentesco in ('AVO','VÓ');


update xls_dependentes set parentesco = 14 where parentesco in ('COMADRE');


update xls_dependentes set parentesco = 15 where parentesco in ('COMPANHEIRO','COMPANHEIRA');


update xls_dependentes set parentesco = 22 where parentesco in ('CONJUGE','CONJUGÊ','ESPOSA','ESPOSO','MARIDO');


update xls_dependentes set parentesco = 16 where parentesco in ('CUNHADA','CUNHADO');


update xls_dependentes set parentesco = 8 where parentesco in ('ENTEADA','ENTEADO','ENTEADO(A)');


update xls_dependentes set parentesco = 1 where parentesco in ('FIHLO','FILHA','FILHA ','FILHO','FILHO(A)');

update xls_dependentes set parentesco =2 where parentesco in ('IMA','IRMA','IRMA ','IRMAO','IRMÃ','IRMÃO','IRM√ÉO(√É)');


update xls_dependentes set parentesco =11 where parentesco in ('MAE','MAE ','MÃE','M√ÉE');


update xls_dependentes set parentesco =9 where parentesco in ('NETA','NETO','NETO(A)');

update xls_dependentes set parentesco =17 where parentesco in ('NORA');

update xls_dependentes set parentesco =18 where parentesco in ('PADASTRO');


update xls_dependentes set parentesco =10 where parentesco in ('PAI','PAI ');

update xls_dependentes set parentesco =3 where parentesco in ('PRIMA','PRIMO','PRIMO(A)');


update xls_dependentes set parentesco =20 where parentesco in ('SOBRI.','SOBRIN','SOBRINHA','SOBRINHO');


update xls_dependentes set parentesco =21 where parentesco in ('SOGRA','SOGRO');

update xls_dependentes set parentesco =4 where parentesco in ('TIA','TIO','TIO(A)');


delete from parentesco where seq_parentesco in (6,7);

insert into convenio (seq_convenio, flg_categoria, nom_fantasia, seq_atividade_convenio, des_telefone, des_beneficio, 
			flg_desativado, flg_filhos, flg_dependentes, flg_conjuge, flg_outros_benef) 
values	(nextval('SEQ_CONVENIO'),'V', 'COSTA VERDE', 5, '7100000000','(para ser descrita...)','N','S','S','S','S');


insert into convenio (seq_convenio, flg_categoria, nom_fantasia, seq_atividade_convenio, des_telefone, des_beneficio, 
			flg_desativado, flg_filhos, flg_dependentes, flg_conjuge, flg_outros_benef) 
values	(nextval('SEQ_CONVENIO'),'V', 'AABB', 5, '7100000000','(para ser descrita...)','N','S','S','S','S');

insert into convenio (seq_convenio, flg_categoria, nom_fantasia, seq_atividade_convenio, des_telefone, des_beneficio, 
			flg_desativado, flg_filhos, flg_dependentes, flg_conjuge, flg_outros_benef) 
values	(nextval('SEQ_CONVENIO'),'V', 'AABB', 5, '7100000000','(para ser descrita...)','N','S','S','S','S');


insert into convenio (seq_convenio, flg_categoria, nom_fantasia, seq_atividade_convenio, des_telefone, des_beneficio, 
			flg_desativado, flg_filhos, flg_dependentes, flg_conjuge, flg_outros_benef) 
values	(nextval('SEQ_CONVENIO'),'F', 'ODONTOSYSTEM', 3, '7100000000','(para ser descrita...)','N','S','S','S','S');


insert into convenio (seq_convenio, flg_categoria, nom_fantasia, seq_atividade_convenio, des_telefone, des_beneficio, 
			flg_desativado, flg_filhos, flg_dependentes, flg_conjuge, flg_outros_benef) 
values	(nextval('SEQ_CONVENIO'),'F', 'PROMEDICA', 3, '7100000000','(para ser descrita...)','N','S','S','S','S');


update xls_vinculacao set convenio = 'ODONTOSYSTEM' where convenio='ODONTO SYSTEM';



insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),2,'BASICO','(descrever plano...)', 0, '01/01/2017', 'N', 'S');

insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),2,'STANDARD','(descrever plano...)', 0, '01/01/2017', 'N', 'S');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),7,'BASICO','(descrever plano...)', 0, '01/01/2017', 'N', 'S');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),7,'EXECUTIVO', '(descrever plano...)',0, '01/01/2017', 'N', 'S');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),5,'BASICO','(descrever plano...)', 0, '01/01/2017', 'N', 'N');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),6,'BASICO','(descrever plano...)', 0, '01/01/2017', 'N', 'N');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),8,'STANDARD', '(descrever plano...)',0, '01/01/2017', 'N', 'S');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),8,'ESPECIAL','(descrever plano...)', 0, '01/01/2017', 'N', 'S');
insert into plano_convenio (seQ_plano, seq_convenio, nom_plano, des_plano, val_plano, dat_inicio_valor, flg_desativado, flg_dedutivel) values (nextval('seq_plano_convenio'),7,'OURO','(descrever plano...)', 0, '01/01/2017', 'N', 'S');

