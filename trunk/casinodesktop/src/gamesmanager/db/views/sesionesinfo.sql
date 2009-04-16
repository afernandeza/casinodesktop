-- View: sesionesinfo

-- DROP VIEW sesionesinfo;

CREATE OR REPLACE VIEW sesionesinfo AS 
 SELECT sesiones.sesionid, sesiones.mesaid, tiposjuego.tipo, sesiones.fichasinicio, sesiones.fichasfin, employeessummary.nombre, sesiones.timeopened, sesiones.timeclosed
   FROM sesiones, tiposjuego, employeessummary, mesas
  WHERE sesiones.mesaid = mesas.mesaid AND mesas.tipojuegoid = tiposjuego.tipojuegoid AND sesiones.empleadoid::text = employeessummary.empleadoid::text
  ORDER BY mesas.mesaid;

ALTER TABLE sesionesinfo OWNER TO casindesktopapp;

