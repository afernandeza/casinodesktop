-- Function: reactivateaccount(character varying)

-- DROP FUNCTION reactivateaccount(character varying);

CREATE OR REPLACE FUNCTION reactivateaccount(eid character varying)
  RETURNS boolean AS
$BODY$
declare
  userid integer;
begin
  select into userid usuarioid from empleados where empleadoid = eid;
  update usuarios set 
    active = true
    where usuarioid = userid;
   return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION reactivateaccount(character varying) OWNER TO postgres;
