-- Table: estadosmexico

-- DROP TABLE estadosmexico;

CREATE TABLE estadosmexico
(
  id integer NOT NULL,
  estado character varying(100) NOT NULL,
  CONSTRAINT estadosmexico_pkey PRIMARY KEY (id),
  CONSTRAINT estadosmexico_estado_key UNIQUE (estado)
)
WITH (OIDS=FALSE);
ALTER TABLE estadosmexico OWNER TO casindesktopapp;
