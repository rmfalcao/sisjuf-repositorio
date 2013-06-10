-- Function: obter_forma_pagamento(int8)

-- DROP FUNCTION obter_forma_pagamento(int8);

CREATE OR REPLACE FUNCTION obter_forma_pagamento(int8)
  RETURNS "varchar" AS
$BODY$
DECLARE
	id_lancamento ALIAS FOR $1;

	retorno varchar(255);
	registro RECORD;
	id_cheque int2;
BEGIN
	retorno := '';

	select into id_cheque cast(str_val_parametro as int2) from parametros where nom_parametro = 'FORMA_PAGTO_CHEQUE';	

	for registro in 
		select fp.nom_forma_pagamento, bl.seq_forma_pagamento, bl.des_banco_cheque_baixa_lancamento, 
		bl.num_agencia_cheque_baixa_lancamento,	bl.dig_agencia_cheque_baixa_lancamento, 
		bl.num_conta_cheque_baixa_lancamento, bl.dig_conta_cheque_baixa_lancamento, bl.num_cheque_baixa_lancamento
		from baixa_lancamento bl natural inner join forma_pagamento fp
		where bl.seq_lancamento = id_lancamento
		order by bl.dat_baixa_lancamento
	LOOP	

		retorno := retorno || registro.nom_forma_pagamento; 
		
		IF registro.seq_forma_pagamento = id_cheque THEN
			retorno := retorno || ' Nº' || registro.num_cheque_baixa_lancamento || ' ';
			retorno := retorno || registro.des_banco_cheque_baixa_lancamento || ' ' || registro.num_agencia_cheque_baixa_lancamento;
			IF (registro.dig_agencia_cheque_baixa_lancamento is not null) OR (registro.dig_agencia_cheque_baixa_lancamento <> '') THEN
				retorno := retorno || '-' || registro.dig_agencia_cheque_baixa_lancamento;
			END IF;
			retorno := retorno || '/' || registro.num_conta_cheque_baixa_lancamento || '-' || registro.dig_conta_cheque_baixa_lancamento;
		END IF;
		
		retorno := retorno || ', '; 

	END LOOP;

	IF retorno <> '' THEN
		retorno := substring(retorno from 1 for (char_length(retorno)-2) );
	ELSE
		retorno := null;
	END IF;
	
	RETURN retorno;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION obter_forma_pagamento(int8) OWNER TO postgres;
