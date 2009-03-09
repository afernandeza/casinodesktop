-- Table: tipoempleados

-- DROP TABLE tipoempleados;

CREATE TABLE tipoempleados
(
  tipoempleadoid integer NOT NULL,
  tipo character(100) NOT NULL,
  CONSTRAINT tipoempleados_pkey PRIMARY KEY (tipoempleadoid)
)
WITH (OIDS=FALSE);
ALTER TABLE tipoempleados OWNER TO casindesktopapp;
