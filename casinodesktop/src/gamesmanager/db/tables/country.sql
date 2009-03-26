-- Table: country

-- DROP TABLE country;

CREATE TABLE country
(
  iso character(2) NOT NULL,
  "name" character varying(80) NOT NULL,
  printable_name character varying(80) NOT NULL,
  iso3 character(3),
  numcode smallint,
  CONSTRAINT country_pkey PRIMARY KEY (iso)
)
WITH (OIDS=FALSE);
ALTER TABLE country OWNER TO casindesktopapp;
