-- Table: direcciones

-- DROP TABLE direcciones;

CREATE TABLE direcciones
(
  direccionid integer NOT NULL,
  callenum character varying(200) NOT NULL,
  numint character varying(100),
  colonia character varying(200) NOT NULL,
  municipio character varying(200) NOT NULL,
  codigopostal character varying(10) NOT NULL,
  estado character varying(200) NOT NULL,
  pais character varying(200) NOT NULL,
  CONSTRAINT pkdirecciones PRIMARY KEY (direccionid),
  CONSTRAINT direcciones_direccionid_fkey FOREIGN KEY (direccionid)
      REFERENCES clientes (direccionid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE DEFERRABLE INITIALLY IMMEDIATE
)
WITH (OIDS=FALSE);
ALTER TABLE direcciones OWNER TO casindesktopapp;
