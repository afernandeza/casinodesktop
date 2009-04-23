-- Function: updatecasino(character varying, character varying)

-- DROP FUNCTION updatecasino(character varying, character varying);

CREATE OR REPLACE FUNCTION updatecasino(sd character varying, jl character varying)
  RETURNS boolean AS
$BODY$
begin
update sucursales set jdbcurl = jl where sucid = sd;
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updatecasino(character varying, character varying) OWNER TO casindesktopapp;
