-- Function: deletegametable(integer)

-- DROP FUNCTION deletegametable(integer);

CREATE OR REPLACE FUNCTION deletegametable(id integer)
  RETURNS boolean AS
$BODY$
BEGIN
DELETE FROM mesas WHERE mesaid = id;
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deletegametable(integer) OWNER TO casindesktopapp;
