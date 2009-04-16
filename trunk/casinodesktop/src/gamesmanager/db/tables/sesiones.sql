-- Table: sesiones

-- DROP TABLE sesiones;

CREATE TABLE sesiones
(
  sesionid integer NOT NULL,
  mesaid integer NOT NULL,
  fichasinicio integer NOT NULL,
  fichasfin integer,
  empleadoid character varying(100) NOT NULL,
  timeopened timestamp with time zone NOT NULL,
  timeclosed timestamp with time zone NOT NULL,
  CONSTRAINT sesiones_pkey PRIMARY KEY (sesionid),
  CONSTRAINT sesiones_empleadoid_fkey FOREIGN KEY (empleadoid)
      REFERENCES empleados (empleadoid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT sesiones_mesaid_fkey FOREIGN KEY (mesaid)
      REFERENCES mesas (mesaid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (OIDS=FALSE);
ALTER TABLE sesiones OWNER TO casindesktopapp;
