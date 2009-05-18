-- Function: insertextuser(character varying, character varying)

-- DROP FUNCTION insertextuser(character varying, character varying);

CREATE OR REPLACE FUNCTION insertextuser(usuario character varying, passwd character varying)
  RETURNS boolean AS
$BODY$
declare
  userid integer;
begin
  select into userid usidauto from nextval('userid') as usidauto;
  insert into usuarios values(userid, usuario, passwd, true, true);
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertextuser(character varying, character varying) OWNER TO casinomngmtapp;
