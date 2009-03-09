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
  CONSTRAINT empleados_pkey PRIMARY KEY (empleadoid),
  CONSTRAINT empleados_direccionid_fkey FOREIGN KEY (direccionid)
      REFERENCES direcciones (direccionid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE,
  CONSTRAINT empleados_tipoempleadoid_fkey FOREIGN KEY (tipoempleadoid)
      REFERENCES tipoempleados (tipoempleadoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT empleados_usuarioid_fkey FOREIGN KEY (usuarioid)
      REFERENCES usuarios (usuarioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE
)
WITH (OIDS=FALSE);
ALTER TABLE empleados OWNER TO casindesktopapp;
