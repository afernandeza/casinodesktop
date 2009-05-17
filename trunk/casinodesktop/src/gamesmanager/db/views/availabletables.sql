-- View: availabletables

-- DROP VIEW availabletables;

CREATE OR REPLACE VIEW availabletables AS 
 SELECT mesas.mesaid, tiposjuego.tipo
   FROM mesas, tiposjuego
  WHERE mesas.tipojuegoid = tiposjuego.tipojuegoid AND NOT (mesas.mesaid IN ( SELECT mesas.mesaid
           FROM sesiones, mesas
          WHERE sesiones.mesaid = mesas.mesaid AND sesiones.timeclosed IS NULL))
  ORDER BY mesas.mesaid;

ALTER TABLE availabletables OWNER TO casindesktopapp;

