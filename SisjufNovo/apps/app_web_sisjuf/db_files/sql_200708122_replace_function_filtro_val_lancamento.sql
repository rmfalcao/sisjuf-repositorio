CREATE OR REPLACE FUNCTION filtro_val_mensalidade(float4, float4, int4)
  RETURNS bool AS
$BODY$
DECLARE

	p_valor_menor ALIAS FOR $1;
	p_valor_maior ALIAS FOR $2;
	seq_associado ALIAS FOR $3;


	valor_menor float4;
	valor_maior float4;
BEGIN

	if p_valor_menor is null Then
		valor_menor = 0;
	else
		valor_menor = p_valor_menor;
	end if;	

	if p_valor_maior is null Then
		valor_maior = 1000000;
	else
		valor_maior = p_valor_maior;
	end if;	

	return exists(select l.val_lancamento 
			from lancamento_associado la
			natural join lancamento l
			natural join baixa_lancamento bl
			where la.seq_associado = seq_associado
			and l.val_lancamento between valor_menor and valor_maior);

END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION filtro_val_mensalidade(float4, float4, int4) OWNER TO admin_asserjuf;
