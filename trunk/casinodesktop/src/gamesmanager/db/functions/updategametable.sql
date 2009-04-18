-- Function: updategametable(integer, integer)

-- DROP FUNCTION updategametable(integer, integer);

CREATE OR REPLACE FUNCTION updategametable(id integer, gt integer)
  RETURNS boolean AS
$BODY$
BEGIN
UPDATE mesas SET tipojuegoid = gt  WHERE mesaid = id;
return true;
END;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updategametable(integer, integer) OWNER TO casindesktopapp;
