-- Function: editcredit(character varying, numeric)

-- DROP FUNCTION editcredit(character varying, numeric);

CREATE OR REPLACE FUNCTION editcredit(id character varying, cantidad numeric)
  RETURNS boolean AS
$BODY$
BEGIN
UPDATE clientes SET credito = credito + cantidad WHERE clienteid = id;
UPDATE clientesexternos SET credito = credito + cantidad WHERE clienteid = id;
return true;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 1;
ALTER FUNCTION editcredit(character varying, numeric) OWNER TO casindesktopapp;
