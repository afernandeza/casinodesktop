-- Function: deleteclient(character varying)

-- DROP FUNCTION deleteclient(character varying);

CREATE OR REPLACE FUNCTION deleteclient(id character varying)
  RETURNS boolean AS
$BODY$
declare
    dirid integer;
begin
    select into dirid direccionid from clientes where clienteid = id;
    delete from clientes where clienteid = id;
    delete from direcciones where direccionid = dirid;
    insert into syncinfo values (nextval('syncinfoid'), 'deleteextclient(''' || id || ''')', current_timestamp);
    return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION deleteclient(character varying) OWNER TO casindesktopapp;
