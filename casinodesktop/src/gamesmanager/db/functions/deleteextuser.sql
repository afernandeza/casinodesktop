-- Function: deleteextuser(character varying)

-- DROP FUNCTION deleteextuser(character varying);

CREATE OR REPLACE FUNCTION deleteextuser(usr character varying)
  RETURNS boolean AS
$BODY$
begin
delete from usuarios where usuario = $1;
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deleteextuser(character varying) OWNER TO casinomngmtapp;
