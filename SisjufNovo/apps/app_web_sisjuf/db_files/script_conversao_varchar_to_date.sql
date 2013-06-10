select * from  historico_evento_associado 

alter table historico_evento_associado add column dt_historico_evento_associado_temp date null

update historico_evento_associado 
    set dt_historico_evento_associado_temp=(select to_date(dat_historico_evento_associado, 'yyyy-mm-dd') 
                                            from historico_evento_associado hea 
                                            where hea.seq_historico_evento_associado=historico_evento_associado.seq_historico_evento_associado
                                            )

alter table historico_evento_associado RENAME COLUMN dat_historico_evento_associado to dat_historico_evento_associado_old

alter table historico_evento_associado RENAME COLUMN dt_historico_evento_associado_temp to dat_historico_evento_associado