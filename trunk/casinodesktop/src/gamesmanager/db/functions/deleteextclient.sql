-- Function: deleteextclient(character varying)

-- DROP FUNCTION deleteextclient(character varying);

CREATE OR REPLACE FUNCTION deleteextclient(id character varying)
  RETURNS boolean AS
$BODY$
begin
    delete from clientesexternos where clienteid = id;
    return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deleteextclient(character varying) OWNER TO casindesktopapp;
