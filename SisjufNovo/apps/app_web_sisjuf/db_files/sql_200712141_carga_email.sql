insert into valor_mensalidade values(nextval('seq_valor_mensalidade'), current_date, 0);

update parametros set str_val_parametro = 'mail.asserjuf.org.br' where nom_parametro = 'SMTP_SERVER';

update parametros set str_val_parametro = 'sisjuf' where nom_parametro = 'SMTP_USER_ACCOUNT';

update parametros set str_val_parametro = 'paulo321' where nom_parametro = 'SMTP_USER_PWD';


select * from parametros