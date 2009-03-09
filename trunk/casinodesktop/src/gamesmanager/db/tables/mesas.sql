-- Table: mesas

-- DROP TABLE mesas;

CREATE TABLE mesas
(
  mesaid integer NOT NULL,
  tipojuegoid integer NOT NULL,
  CONSTRAINT mesas_pkey PRIMARY KEY (mesaid),
  CONSTRAINT mesas_tipojuegoid_fkey FOREIGN KEY (tipojuegoid)
      REFERENCES tiposjuego (tipojuegoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE mesas OWNER TO casindesktopapp;
