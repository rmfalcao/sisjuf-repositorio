alter table conta_banco 
add constraint fk_conta_banco_banco 
foreign key (seq_banco) 
REFERENCES banco (seq_banco)