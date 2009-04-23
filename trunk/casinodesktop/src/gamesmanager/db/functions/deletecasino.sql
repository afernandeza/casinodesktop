-- Function: deletecasino(character varying)

-- DROP FUNCTION deletecasino(character varying);

CREATE OR REPLACE FUNCTION deletecasino(id character varying)
  RETURNS boolean AS
$BODY$
begin
    delete from sucursales where sucid = id;
    return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deletecasino(character varying) OWNER TO casindesktopapp;
