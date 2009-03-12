-- Table: usuarios

-- DROP TABLE usuarios;

CREATE TABLE usuarios
(
  usuarioid integer NOT NULL,
  usuario character varying(100) NOT NULL,
  "password" character varying(100) NOT NULL,
  CONSTRAINT usuarios_pkey PRIMARY KEY (usuarioid)
)
WITH (OIDS=FALSE);
ALTER TABLE usuarios OWNER TO casindesktopapp;
