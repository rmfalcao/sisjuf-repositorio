-- Function: consolidar_historico_saldo()

-- DROP FUNCTION consolidar_historico_saldo();

CREATE OR REPLACE FUNCTION consolidar_historico_saldo()
  RETURNS bool AS
$BODY$
DECLARE

	row_conta conta%ROWTYPE;
	ultima_data date;
	--hoje date;
	val_ultima_data numeric(14,2) := 0;
	soma_lancamentos numeric(14,2) DEFAULT 0;
  
BEGIN
	--SELECT INTO hoje CURRENT_DATE;

	--INICIO: Percorre toda a tabela conta
	FOR row_conta IN SELECT * FROM conta LOOP

		--Busca a última data que tem em historico saldo para a conta[i] (ultima_data) 
		SELECT INTO ultima_data MAX(dat_historico_saldo) FROM historico_saldo WHERE seq_conta = row_conta.seq_conta;

		IF ultima_data IS NULL THEN
		
			--busta soma de todos os valores de lancamento onde data = última_data e conta = seq_conta (soma_lancamentos)
			SELECT INTO soma_lancamentos SUM(bl.val_baixa_lancamento) FROM baixa_lancamento bl 
				NATURAL INNER JOIN lancamento l WHERE l.seq_conta = row_conta.seq_conta;

			IF soma_lancamentos IS NULL THEN
				soma_lancamentos := 0;
			END IF;

			INSERT INTO historico_saldo (seq_conta, dat_historico_saldo, val_historico_saldo)
				VALUES (row_conta.seq_conta, CURRENT_DATE, soma_lancamentos);


		ELSE

			--INICIO: Loop de última_data+1 (i) até hoje
			WHILE ultima_data + 1 <= CURRENT_DATE LOOP
	
				--busca valor em historico_saldo onde data = ultima_data e conta = conta[i] (val_ultima_data)
				SELECT INTO val_ultima_data val_historico_saldo FROM historico_saldo 
					WHERE seq_conta = row_conta.seq_conta AND dat_historico_saldo = ultima_data;
	
				--busta soma de todos os valores de lancamento onde data = última_data e conta = seq_conta (soma_lancamentos)
				SELECT SUM(bl.val_baixa_lancamento) FROM baixa_lancamento bl NATURAL INNER JOIN lancamento l
					WHERE l.seq_conta = row_conta.seq_conta AND bl.dat_baixa_lancamento = ultima_data;
	
				IF soma_lancamentos IS NULL THEN
					soma_lancamentos := 0;
				END IF;
	
				--val_ultima_data + soma_lancamento (val_setar)
				--insere um registro em historico_saldo com data = i e valor = val_setar e conta = seq_conta
				INSERT INTO historico_saldo (seq_conta, dat_historico_saldo, val_historico_saldo)
					VALUES (row_conta.seq_conta, ultima_data + 1, val_ultima_data + soma_lancamentos);
	
	
				--última_data = última_data + 1
				ultima_data := ultima_data + 1;
	
			END LOOP;
	
		END IF;
	
	END LOOP;
	
	RETURN TRUE;

END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION consolidar_historico_saldo() OWNER TO postgres;
