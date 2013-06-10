CREATE OR REPLACE FUNCTION filtro_val_mensalidade(real, real, int4)
  RETURNS boolean AS
$BODY$
DECLARE

	p_valor_menor ALIAS FOR $1;
	p_valor_maior ALIAS FOR $2;
	seq_associado ALIAS FOR $3;


	valor_menor real;
	valor_maior real;
BEGIN

	if p_valor_menor is null Then
		valor_menor = 0;
	end if;	

	if p_valor_maior is null Then
		valor_maior = 1000000;
	end if;	

	return exists(select l.val_lancamento 
			from lancamento_associado la
			natural join lancamento l
			natural join baixa_lancamento bl
			where la.seq_associado = a.seq_associado
			and l.val_lancamnto between valor_menor and valor_maior	);

END
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION filtro_val_mensalidade(real, real, int4) OWNER TO admin_asserjuf;


