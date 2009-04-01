-- Function: insertgametype(character varying)

-- DROP FUNCTION insertgametype(character varying);

CREATE OR REPLACE FUNCTION insertgametype(tj character varying)
  RETURNS boolean AS
$BODY$
BEGIN
INSERT INTO tiposjuego VALUES(nextval('gametypeid'), tj);
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertgametype(character varying) OWNER TO casindesktopapp;
