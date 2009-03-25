-- Function: deleteclient(character varying)

-- DROP FUNCTION deleteclient(character varying);

CREATE OR REPLACE FUNCTION deleteclient(id character varying)
  RETURNS void AS
$BODY$
declare
    dirid integer;
begin
    select into dirid direccionid from empleados where empleadoid = id;
    delete from empleados where empleadoid = id;
    delete from direcciones where direccionid = dirid;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deleteclient(character varying) OWNER TO casindesktopapp;
