-- Sequence: clientid

-- DROP SEQUENCE clientid;

CREATE SEQUENCE clientid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE clientid OWNER TO casindesktopapp;
