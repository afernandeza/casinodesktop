-- Function: userexists(character varying)

-- DROP FUNCTION userexists(character varying);

CREATE OR REPLACE FUNCTION userexists(usr character varying)
  RETURNS boolean AS
$BODY$
declare
  cont integer;
begin
  select into cont count(*) from usuarios where usuario = usr;
  if cont = 0 then return false;
  else return true;
  end if;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION userexists(character varying) OWNER TO casindesktopapp;
