-- Function: closegamesession(integer, numeric)

-- DROP FUNCTION closegamesession(integer, numeric);

CREATE OR REPLACE FUNCTION closegamesession(id integer, ff numeric)
  RETURNS boolean AS
$BODY$
BEGIN
UPDATE sesiones SET fichasfin = ff, timeclosed = current_timestamp  WHERE sesionid = id;
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION closegamesession(integer, numeric) OWNER TO casindesktopapp;
