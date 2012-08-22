-- Language: plpgsql

-- DROP LANGUAGE plpgsql;

 CREATE TRUSTED PROCEDURAL LANGUAGE 'plpgsql'
  HANDLER plpgsql_call_handler
  VALIDATOR plpgsql_validator;