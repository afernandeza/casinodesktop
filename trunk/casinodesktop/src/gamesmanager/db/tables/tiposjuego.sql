-- Table: tiposjuego

-- DROP TABLE tiposjuego;

CREATE TABLE tiposjuego
(
  tipojuegoid integer NOT NULL,
  tipo character varying(100) NOT NULL,
  CONSTRAINT tiposjuego_pkey PRIMARY KEY (tipojuegoid)
)
WITH (OIDS=FALSE);
ALTER TABLE tiposjuego OWNER TO casindesktopapp;
