-- Function: insertgametable(integer, integer)

-- DROP FUNCTION insertgametable(integer, integer);

CREATE OR REPLACE FUNCTION insertgametable(tid integer, gtid integer)
  RETURNS boolean AS
$BODY$
BEGIN
INSERT INTO mesas VALUES(tid, gtid);
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertgametable(integer, integer) OWNER TO casindesktopapp;
