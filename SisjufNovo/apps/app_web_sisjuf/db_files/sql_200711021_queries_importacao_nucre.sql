-- tem no banco que não estão na planilha

SELECT NUM_MATRICULA_JUSTICA_ASSOCIADO as matri, nom_associado, 'banco' as excesso FROM VW_ASSOCIADO 
WHERE NUM_MATRICULA_JUSTICA_ASSOCIADO NOT IN ( SELECT MATRICULA FROM(
		select 1 as matricula, 'Rodrigo' as nome
		union all
		select 2222 as matricula, 'Paulo' as nome) TAB
	)
union
 SELECT MATRICULA, NOME, 'arquivo' as excesso FROM(
		select 1 as matricula, 'Rodrigo' as nome
		union all
		select 2222 as matricula, 'Paulo' as nome) TAB
 WHERE TAB.MATRICULA NOT IN (SELECT NUM_MATRICULA_JUSTICA_ASSOCIADO FROM VW_ASSOCIADO )


