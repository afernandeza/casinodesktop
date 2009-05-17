-- View: availableemployees

-- DROP VIEW availableemployees;

CREATE OR REPLACE VIEW availableemployees AS 
 SELECT employeessummary.empleadoid, employeessummary.nombre
   FROM employeessummary
  WHERE NOT (employeessummary.nombre IN ( SELECT employeessummary.nombre
           FROM sesiones, tiposjuego, employeessummary, mesas
          WHERE sesiones.mesaid = mesas.mesaid AND mesas.tipojuegoid = tiposjuego.tipojuegoid AND sesiones.empleadoid::text = employeessummary.empleadoid::text AND sesiones.timeclosed IS NULL))
  ORDER BY employeessummary.nombre;

ALTER TABLE availableemployees OWNER TO casindesktopapp;

