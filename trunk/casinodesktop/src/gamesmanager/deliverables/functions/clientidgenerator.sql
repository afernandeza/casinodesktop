-- Function: clientidgenerator()

-- DROP FUNCTION clientidgenerator();

CREATE OR REPLACE FUNCTION clientidgenerator()
  RETURNS character varying AS
$BODY$SELECT 'SUCA' || nextval('clientid')$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 1;
ALTER FUNCTION clientidgenerator() OWNER TO casindesktopapp;
