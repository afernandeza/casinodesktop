-- Function: findclient(character varying)

-- DROP FUNCTION findclient(character varying);

CREATE OR REPLACE FUNCTION findclient(id character varying)
  RETURNS clientsdata AS
$BODY$
SELECT * FROM clientsdata WHERE clienteid = $1;
$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100;
ALTER FUNCTION findclient(character varying) OWNER TO casindesktopapp;
