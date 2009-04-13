-- Function: findexternalclient(character varying)

-- DROP FUNCTION findexternalclient(character varying);

CREATE OR REPLACE FUNCTION findexternalclient(id character varying)
  RETURNS clientesexternos AS
$BODY$
SELECT * FROM clientesexternos WHERE clienteid = $1;
$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100;
ALTER FUNCTION findexternalclient(character varying) OWNER TO postgres;
