-- Function: updatelatestsid(character varying, integer)

-- DROP FUNCTION updatelatestsid(character varying, integer);

CREATE OR REPLACE FUNCTION updatelatestsid(cid character varying, lid integer)
  RETURNS boolean AS
$BODY$
begin
  update sucursales set latestsyncedqid = lid where sucid = cid;
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updatelatestsid(character varying, integer) OWNER TO casindesktopapp;
