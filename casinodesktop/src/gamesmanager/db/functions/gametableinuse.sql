-- Function: gametableinuse(integer)

-- DROP FUNCTION gametableinuse(integer);

CREATE OR REPLACE FUNCTION gametableinuse(tid integer)
  RETURNS boolean AS
$BODY$
declare
  conteo integer;
begin
 SELECT into conteo count(*)
   FROM sesiones, tiposjuego, employeessummary, mesas
  WHERE sesiones.mesaid = mesas.mesaid AND 
  mesas.tipojuegoid = tiposjuego.tipojuegoid AND 
  sesiones.empleadoid::text = employeessummary.empleadoid::text 
  AND sesiones.timeclosed IS NULL and sesiones.mesaid = $1;
  if conteo = 0 then
    return false;
  else 
    return true;
  end if;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION gametableinuse(integer) OWNER TO casindesktopapp;
