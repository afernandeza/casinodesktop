-- View: repmesas

-- DROP VIEW repmesas;

CREATE OR REPLACE VIEW repmesas AS 
 SELECT sesiones.mesaid, tiposjuego.tipo, sum(sesiones.fichasfin - sesiones.fichasinicio) AS sum
   FROM sesiones, mesas, tiposjuego
  WHERE sesiones.mesaid = mesas.mesaid AND mesas.tipojuegoid = tiposjuego.tipojuegoid
  GROUP BY sesiones.mesaid, tiposjuego.tipo
  ORDER BY sum(sesiones.fichasfin - sesiones.fichasinicio) DESC;

ALTER TABLE repmesas OWNER TO casinomngmtapp;

