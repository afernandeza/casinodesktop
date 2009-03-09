-- Function: insertclient(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying)

-- DROP FUNCTION insertclient(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying);

CREATE OR REPLACE FUNCTION insertclient(credito numeric, nombres character varying, appaterno character varying, apmaterno character varying, sexo character, fechanac date, foto bytea, telcasa character varying, telcel character varying)
  RETURNS character varying AS
$BODY$
declare
  clientid varchar;
begin
  select into clientid clientidgenerator from clientidgenerator();
  return clientid;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertclient(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying) OWNER TO casindesktopapp;
