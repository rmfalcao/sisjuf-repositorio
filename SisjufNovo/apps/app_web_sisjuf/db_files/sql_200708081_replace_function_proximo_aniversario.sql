-- Function: proximo_aniversario(date)

-- DROP FUNCTION proximo_aniversario(date);

CREATE OR REPLACE FUNCTION proximo_aniversario(date)
  RETURNS date AS
$BODY$
DECLARE
	data ALIAS FOR $1;

BEGIN

RETURN

	(case 
	When date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data)) < CURRENT_DATE Then date((extract(Year from CURRENT_DATE)+1)||'-'||extract(month from data)||'-'||extract(day from data))
	Else date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data))
	end) as retorno ;

--	select (case 
--	When date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data)) < CURRENT_DATE Then date((extract(Year from CURRENT_DATE)+1)||'-'||extract(month from data)||'-'||extract(day from data))
--	Else date(extract(Year from CURRENT_DATE)||'-'||extract(month from data)||'-'||extract(day from data))
--	end) as retorno ;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION proximo_aniversario(date) OWNER TO admin_asserjuf;