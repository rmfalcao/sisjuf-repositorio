CREATE OR REPLACE FUNCTION remove_pessoa()
RETURNS trigger AS
$BODY$
DECLARE

BEGIN

	delete from pessoa where seq_pessoa = old.seq_pessoa;
	
	return new;

END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE;
ALTER FUNCTION remove_pessoa() OWNER TO admin_asserjuf;

----------------------------

CREATE TRIGGER tg_remove_filho AFTER DELETE
    ON filho FOR EACH ROW
    EXECUTE PROCEDURE remove_pessoa (  );

----------------------------

CREATE TRIGGER tg_remove_dependente AFTER DELETE
    ON dependente FOR EACH ROW
    EXECUTE PROCEDURE remove_pessoa (  );

----------------------------