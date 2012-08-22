INSERT INTO PARAMETROS VALUES ('NUM_DIAS_PROX_ANIVER', '20');



CREATE OR REPLACE FUNCTION proximo_aniversario(date)
  RETURNS date AS	
$BODY$
DECLARE
	data ALIAS FOR $1;

BEGIN

RETURN

	(case 
	When date(extract(Year from CURRENT_DATE)||extract(month from data)||extract(day from data)) < CURRENT_DATE Then (extract(Year from CURRENT_DATE)+1)||extract(month from data)||extract(day from data)
	Else extract(Year from CURRENT_DATE)||extract(month from data)||extract(day from data)
	end) as retorno ;


END;
$BODY$
LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION proximo_aniversario(date) OWNER TO admin_asserjuf

