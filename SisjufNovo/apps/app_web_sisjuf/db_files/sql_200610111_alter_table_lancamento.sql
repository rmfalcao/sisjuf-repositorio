alter table lancamento drop column tabela_x;
alter table lancamento drop column nom_campo_x;
alter table lancamento drop column val_campo_x;


alter table  lancamento add dat_efetivacao_lancamento date 		null;
alter table  lancamento add tabela_x 	varchar(30) 	null;
alter table  lancamento add nom_campo_x 	varchar(30) 	null;
alter table  lancamento add val_campo_x	varchar(30) 	null;