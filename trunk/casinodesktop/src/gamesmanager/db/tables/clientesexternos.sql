-- Table: clientesexternos

-- DROP TABLE clientesexternos;

CREATE TABLE clientesexternos
(
  casinoid integer NOT NULL,
  clienteid character varying(100) NOT NULL,
  CONSTRAINT clientesexternos_pkey PRIMARY KEY (casinoid, clienteid)
)
WITH (OIDS=FALSE);
ALTER TABLE clientesexternos OWNER TO casindesktopapp;
