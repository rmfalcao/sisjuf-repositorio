-- Function: obter_saldo(int2)

-- DROP FUNCTION obter_saldo(int2);

CREATE OR REPLACE FUNCTION obter_saldo(int2)
  RETURNS "numeric" AS
$BODY$
DECLARE
	id_conta ALIAS FOR $1;

	ultima_data date;
	--hoje date;
	saldo numeric(14,2) := 0;
	val_ultima_data numeric(14,2) DEFAULT 0;
  
BEGIN

	--Busca a última data que tem em historico saldo para a conta[i] (ultima_data) 
	SELECT INTO ultima_data MAX(dat_historico_saldo) FROM historico_saldo WHERE seq_conta = id_conta;

	IF ultima_data IS NULL THEN
	
		--busta soma de todos os valores de lancamento onde data = última_data e conta = seq_conta (soma_lancamentos)
		SELECT INTO saldo SUM(bl.val_baixa_lancamento) FROM baixa_lancamento bl
			NATURAL INNER JOIN lancamento l	WHERE l.seq_conta = id_conta;

		IF saldo IS NULL THEN
			saldo := 0;
		END IF;

	ELSE

		--busca valor em historico_saldo onde data = ultima_data e conta = conta[i] (val_ultima_data)
		SELECT INTO val_ultima_data val_historico_saldo FROM historico_saldo 
			WHERE seq_conta = id_conta AND dat_historico_saldo = ultima_data;

		--busta soma de todos os valores de lancamento onde data = última_data e conta = seq_conta (soma_lancamentos)
		SELECT INTO saldo SUM(bl.val_baixa_lancamento) FROM baixa_lancamento bl NATURAL INNER JOIN lancamento l
			WHERE l.seq_conta = id_conta AND bl.dat_baixa_lancamento >= ultima_data;

		IF saldo IS NULL THEN
			saldo := 0;
		END IF;

		saldo := saldo + val_ultima_data;

	END IF;

	RETURN saldo;

END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION obter_saldo(int2) OWNER TO admin_asserjuf;
