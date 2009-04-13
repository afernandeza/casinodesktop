-- View: mesasinfo

-- DROP VIEW mesasinfo;

CREATE OR REPLACE VIEW mesasinfo AS 
 SELECT mesas.mesaid, tiposjuego.tipo
   FROM mesas, tiposjuego
  WHERE mesas.tipojuegoid = tiposjuego.tipojuegoid
  ORDER BY mesas.mesaid;

ALTER TABLE mesasinfo OWNER TO casindesktopapp;

