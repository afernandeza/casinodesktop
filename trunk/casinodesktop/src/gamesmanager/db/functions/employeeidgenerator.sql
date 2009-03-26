-- Function: employeeidgenerator()

-- DROP FUNCTION employeeidgenerator();

CREATE OR REPLACE FUNCTION employeeidgenerator()
  RETURNS character varying AS
$BODY$SELECT 'SUCA_E' || nextval('employeeid')$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 1;
ALTER FUNCTION employeeidgenerator() OWNER TO casindesktopapp;
