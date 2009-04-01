-- Function: deletegametype(character varying)

-- DROP FUNCTION deletegametype(character varying);

CREATE OR REPLACE FUNCTION deletegametype(tj character varying)
  RETURNS boolean AS
$BODY$
BEGIN
DELETE FROM tiposjuego WHERE tipo = tj;
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deletegametype(character varying) OWNER TO casindesktopapp;
