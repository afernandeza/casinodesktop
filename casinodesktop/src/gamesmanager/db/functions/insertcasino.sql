-- Function: insertcasino(character varying, character varying, character varying, character varying)

-- DROP FUNCTION insertcasino(character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION insertcasino(sd character varying, jl character varying, h character varying, ds character varying)
  RETURNS boolean AS
$BODY$
begin
insert into sucursales values(sd, jl, h, ds, 0);
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertcasino(character varying, character varying, character varying, character varying) OWNER TO casindesktopapp;
