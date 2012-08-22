CREATE TRIGGER tg_remove_conjuge
  AFTER DELETE
  ON conjuge
  FOR EACH ROW
  EXECUTE PROCEDURE remove_pessoa();

