-- Function: insertgamesession(integer, numeric, character varying)

-- DROP FUNCTION insertgamesession(integer, numeric, character varying);

CREATE OR REPLACE FUNCTION insertgamesession(md integer, fi numeric, eid character varying)
  RETURNS boolean AS
$BODY$
BEGIN
INSERT INTO sesiones VALUES(nextval('sessionid'), md, fi, null, eid, current_timestamp, null);
return true;
END;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertgamesession(integer, numeric, character varying) OWNER TO casindesktopapp;
