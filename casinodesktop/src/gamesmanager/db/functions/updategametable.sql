-- Function: updategametable(integer, numeric)

-- DROP FUNCTION updategametable(integer, numeric);

CREATE OR REPLACE FUNCTION updategametable(id integer, gt numeric)
  RETURNS boolean AS
$BODY$
BEGIN
UPDATE mesas SET tipojuegoid = gt  WHERE mesaid = id;
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updategametable(integer, numeric) OWNER TO casindesktopapp;
