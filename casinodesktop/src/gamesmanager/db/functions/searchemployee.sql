-- Function: searchemployees(character varying)

-- DROP FUNCTION searchemployees(character varying);

CREATE OR REPLACE FUNCTION searchemployees(string character varying)
  RETURNS SETOF employeessummary AS
$BODY$
SELECT empleadoid, tipo, nombre, usuario, active, fechareg, telcasa, 
       telcel, despedido
  FROM employeessummary
WHERE lower(empleadoid || tipo || nombre || usuario || telcasa || telcel)
 LIKE lower('%' || $1 || '%')
 ORDER BY nombre;

$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION searchemployees(character varying) OWNER TO casindesktopapp;
