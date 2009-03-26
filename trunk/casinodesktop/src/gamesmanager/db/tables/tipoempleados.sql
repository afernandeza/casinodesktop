-- Table: tipoempleados

-- DROP TABLE tipoempleados;

CREATE TABLE tipoempleados
(
  tipoempleadoid integer NOT NULL,
  tipo character varying(100) NOT NULL,
  CONSTRAINT tipoempleados_pkey PRIMARY KEY (tipoempleadoid),
  CONSTRAINT tipoempleados_tipo_key UNIQUE (tipo)
)
WITH (OIDS=FALSE);
ALTER TABLE tipoempleados OWNER TO casindesktopapp;
