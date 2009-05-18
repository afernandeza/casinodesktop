-- Function: updateextuser(character varying, character varying)

-- DROP FUNCTION updateextuser(character varying, character varying);

CREATE OR REPLACE FUNCTION updateextuser(usr character varying, passwd character varying)
  RETURNS boolean AS
$BODY$
begin
update usuarios set password = passwd where usuario = usr;
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updateextuser(character varying, character varying) OWNER TO casinomngmtapp;
