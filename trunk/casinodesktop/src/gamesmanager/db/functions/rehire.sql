-- Function: rehire(character varying)

-- DROP FUNCTION rehire(character varying);

CREATE OR REPLACE FUNCTION rehire(eid character varying)
  RETURNS boolean AS
$BODY$
begin
  update empleados set 
    despedido = null
    where empleadoid = eid;
   return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION rehire(character varying) OWNER TO casindesktopapp;
