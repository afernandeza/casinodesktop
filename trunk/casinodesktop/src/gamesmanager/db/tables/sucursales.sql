-- Table: sucursales

-- DROP TABLE sucursales;

CREATE TABLE sucursales
(
  sucid character varying(100) NOT NULL,
  jdbcurl character varying(100) NOT NULL,
  host character varying(100) NOT NULL,
  dbms character varying(100) NOT NULL,
  CONSTRAINT sucursales_pkey PRIMARY KEY (sucid),
  CONSTRAINT sucursales_jdbcurl_key UNIQUE (jdbcurl)
)
WITH (OIDS=FALSE);
ALTER TABLE sucursales OWNER TO casindesktopapp;
