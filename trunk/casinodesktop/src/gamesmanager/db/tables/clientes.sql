-- Table: clientes

-- DROP TABLE clientes;

CREATE TABLE clientes
(
  direccionid integer NOT NULL,
  credito numeric(15,2) NOT NULL,
  nombres character varying(100) NOT NULL,
  appaterno character varying(100) NOT NULL,
  apmaterno character varying(100),
  sexo character(1) NOT NULL,
  fechanac date NOT NULL,
  foto bytea NOT NULL,
  fechareg date NOT NULL,
  telcasa character varying(100),
  telcel character varying(100) NOT NULL,
  clienteid character varying(100) NOT NULL,
  CONSTRAINT clientes_pkey PRIMARY KEY (clienteid),
  CONSTRAINT clientes_direccionid_key UNIQUE (direccionid),
  CONSTRAINT clientes_nombres_key UNIQUE (nombres, appaterno, apmaterno, fechanac)
)
WITH (OIDS=FALSE);
ALTER TABLE clientes OWNER TO casindesktopapp;

-- Index: clientidindex

-- DROP INDEX clientidindex;

CREATE INDEX clientidindex
  ON clientes
  USING btree
  (clienteid);

