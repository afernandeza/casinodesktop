-- View: opensessionsinfo

-- DROP VIEW opensessionsinfo;

CREATE OR REPLACE VIEW opensessionsinfo AS 
 SELECT sesiones.sesionid, sesiones.mesaid, tiposjuego.tipo, sesiones.fichasinicio, sesiones.fichasfin, employeessummary.nombre, sesiones.timeopened, sesiones.timeclosed
   FROM sesiones, tiposjuego, employeessummary, mesas
  WHERE sesiones.mesaid = mesas.mesaid AND mesas.tipojuegoid = tiposjuego.tipojuegoid AND sesiones.empleadoid::text = employeessummary.empleadoid::text AND sesiones.timeclosed IS NULL
  ORDER BY mesas.mesaid;

ALTER TABLE opensessionsinfo OWNER TO casindesktopapp;

