-- Function: repsesiones(date, date)

-- DROP FUNCTION repsesiones(date, date);

CREATE OR REPLACE FUNCTION repsesiones(st date, ed date)
  RETURNS bigint AS
$BODY$
 SELECT sum(sesiones.fichasfin - sesiones.fichasinicio) AS sum
   FROM sesiones
  WHERE (sesiones.timeopened, sesiones.timeclosed) OVERLAPS ($1, $2) 
  AND sesiones.timeclosed IS NOT NULL
  $BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100;
ALTER FUNCTION repsesiones(date, date) OWNER TO casinomngmtapp;
