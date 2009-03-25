-- Function: deleteemployee(character varying)

-- DROP FUNCTION deleteemployee(character varying);

CREATE OR REPLACE FUNCTION deleteemployee(id character varying)
  RETURNS void AS
$BODY$
declare
    dirid integer;
    uid integer;
begin
    select into dirid direccionid from empleados where empleadoid = id;
    select into uid usuarioid from empleados where empleadoid = id;
    delete from empleados where empleadoid = id;
    delete from direcciones where direccionid = dirid;
    delete from usuarios where usuarioid = uid;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deleteemployee(character varying) OWNER TO casindesktopapp;
