-- View: repempleados

-- DROP VIEW repempleados;

CREATE OR REPLACE VIEW repempleados AS 
 SELECT sesiones.empleadoid, empleados.appaterno, empleados.apmaterno, empleados.nombres, sum(sesiones.fichasfin - sesiones.fichasinicio) AS sum
   FROM sesiones, empleados
  WHERE sesiones.empleadoid::text = empleados.empleadoid::text AND sesiones.timeclosed IS NOT NULL
  GROUP BY sesiones.empleadoid, empleados.appaterno, empleados.apmaterno, empleados.nombres
  ORDER BY sum(sesiones.fichasfin - sesiones.fichasinicio) DESC;

ALTER TABLE repempleados OWNER TO casinomngmtapp;

