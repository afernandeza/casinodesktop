-- Table: empleados

-- DROP TABLE empleados;

CREATE TABLE empleados
(
  direccionid integer NOT NULL,
  nombres character varying(100) NOT NULL,
  appaterno character varying(100) NOT NULL,
  apmaterno character varying(100),
  sexo character(1) NOT NULL,
  fechanac date NOT NULL,
  foto bytea NOT NULL,
  usuarioid integer NOT NULL,
  tipoempleadoid integer NOT NULL,
  telcasa character varying(100),
  telcel character varying(100) NOT NULL,
  empleadoid character varying(100) NOT NULL,
  fechareg date NOT NULL,
  despedido date,
  CONSTRAINT empleados_pkey PRIMARY KEY (empleadoid),
  CONSTRAINT empleados_direccionid_key UNIQUE (direccionid),
  CONSTRAINT empleados_nombres_key UNIQUE (nombres, appaterno, apmaterno, fechanac)
)
WITH (OIDS=FALSE);
ALTER TABLE empleados OWNER TO casindesktopapp;

-- Index: empleadoidindex

-- DROP INDEX empleadoidindex;

CREATE INDEX empleadoidindex
  ON empleados
  USING btree
  (empleadoid);

