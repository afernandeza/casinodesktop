-- Function: deactivateaccount(character varying)

-- DROP FUNCTION deactivateaccount(character varying);

CREATE OR REPLACE FUNCTION deactivateaccount(eid character varying)
  RETURNS boolean AS
$BODY$
declare
  userid integer;
begin
  select into userid usuarioid from empleados where empleadoid = eid;
  update usuarios set 
    active = false
    where usuarioid = userid;
   return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deactivateaccount(character varying) OWNER TO postgres;
