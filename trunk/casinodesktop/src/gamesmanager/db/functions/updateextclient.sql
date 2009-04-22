-- Function: updateextclient(character varying, character varying)

-- DROP FUNCTION updateextclient(character varying, character varying);

CREATE OR REPLACE FUNCTION updateextclient(id character varying, n character varying)
  RETURNS boolean AS
$BODY$
begin
  update clientesexternos set nombre = n where clienteid = id;
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updateextclient(character varying, character varying) OWNER TO casindesktopapp;
