-- Sequence: gametypeid

-- DROP SEQUENCE gametypeid;

CREATE SEQUENCE gametypeid
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE gametypeid OWNER TO casindesktopapp;
