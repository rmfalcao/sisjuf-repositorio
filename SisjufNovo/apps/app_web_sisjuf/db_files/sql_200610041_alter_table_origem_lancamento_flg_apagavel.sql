
alter table origem_lancamento drop column flg_nao_apagavel;

alter table origem_lancamento add flg_nao_apagavel_origem_lancamento bit(1) default '0' not null;

update origem_lancamento set flg_nao_apagavel_origem_lancamento = '1' where seq_origem_lancamento = 0;

