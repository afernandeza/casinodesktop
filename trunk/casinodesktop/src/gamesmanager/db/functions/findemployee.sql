-- Function: finde(character varying)

-- DROP FUNCTION finde(character varying);

CREATE OR REPLACE FUNCTION findemployee(id character varying)
  RETURNS employeesdata AS
$BODY$
SELECT * FROM employeesdata WHERE empleadoid = $1;
$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100;
ALTER FUNCTION findemployee(character varying) OWNER TO casindesktopapp;
