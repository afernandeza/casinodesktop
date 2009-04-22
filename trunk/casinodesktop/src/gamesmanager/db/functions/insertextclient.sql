-- Function: insertextclient(character varying, character varying, character varying)

-- DROP FUNCTION insertextclient(character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION insertextclient(sucid character varying, cid character varying, nombre character varying)
  RETURNS boolean AS
$BODY$
begin
  insert into clientesexternos values (sucid, cid, 0, nombre);
  return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertextclient(character varying, character varying, character varying) OWNER TO casindesktopapp;
