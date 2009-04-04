-- Table: usuarios

-- DROP TABLE usuarios;

CREATE TABLE usuarios
(
  usuarioid integer NOT NULL,
  usuario character varying(100) NOT NULL,
  "password" character varying(100) NOT NULL,
  active boolean NOT NULL DEFAULT true,
  externo boolean NOT NULL DEFAULT false,
  CONSTRAINT usuarios_pkey PRIMARY KEY (usuarioid),
  CONSTRAINT usuarios_usuario_key UNIQUE (usuario)
)
WITH (OIDS=FALSE);
ALTER TABLE usuarios OWNER TO casindesktopapp;

-- Index: usernameindex

-- DROP INDEX usernameindex;

CREATE INDEX usernameindex
  ON usuarios
  USING btree
  (usuario);

