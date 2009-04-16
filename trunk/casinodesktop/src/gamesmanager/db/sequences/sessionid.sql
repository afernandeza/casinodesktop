-- Sequence: sessionid

-- DROP SEQUENCE sessionid;

CREATE SEQUENCE sessionid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE sessionid OWNER TO casindesktopapp;
