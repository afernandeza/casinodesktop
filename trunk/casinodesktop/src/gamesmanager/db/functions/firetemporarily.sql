-- Function: firetemporarily(character varying)

-- DROP FUNCTION firetemporarily(character varying);

CREATE OR REPLACE FUNCTION firetemporarily(eid character varying)
  RETURNS boolean AS
$BODY$
declare
  userid integer;
begin
  select into userid usuarioid from empleados where empleadoid = eid;
  update empleados set 
    despedido = current_date
    where empleadoid = eid;
  update usuarios set 
    active = false
    where usuarioid = userid;
   return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION firetemporarily(character varying) OWNER TO casindesktopapp;
