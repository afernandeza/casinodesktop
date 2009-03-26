-- Function: authenticate(character varying, character varying)

-- DROP FUNCTION authenticate(character varying, character varying);

CREATE OR REPLACE FUNCTION authenticate(un character varying, pw character varying)
  RETURNS boolean AS
$BODY$
declare
  cont integer;
begin
  select into cont count(*) from usuarios 
  where usuario = un AND password = md5(pw);
  if cont = 1 then
    return true;
  else 
    return false;
  end if;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION authenticate(character varying, character varying) OWNER TO postgres;
