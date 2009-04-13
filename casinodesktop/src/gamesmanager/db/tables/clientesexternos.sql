-- Table: clientesexternos

-- DROP TABLE clientesexternos;

CREATE TABLE clientesexternos
(
  casinoid character varying(100) NOT NULL,
  clienteid character varying(100) NOT NULL,
  CONSTRAINT clientesexternos_pkey PRIMARY KEY (casinoid, clienteid),
  CONSTRAINT clientesexternos_clienteid_key UNIQUE (clienteid)
)
WITH (OIDS=FALSE);
ALTER TABLE clientesexternos OWNER TO casindesktopapp;

-- Index: clientesexternosindex

-- DROP INDEX clientesexternosindex;

CREATE INDEX clientesexternosindex
  ON clientesexternos
  USING btree
  (clienteid);

