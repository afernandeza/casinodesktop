-- Function: getlatestsyncedid(character varying)

-- DROP FUNCTION getlatestsyncedid(character varying);

CREATE OR REPLACE FUNCTION getlatestsyncedid(sd character varying)
  RETURNS integer AS
$BODY$
declare
  cont integer;
  syncid integer;
begin
  select into cont count(*) from sucursales where sucid = sd;
  if cont = 1 then
    select into syncid latestsyncedqid from sucursales where sucid = sd;
    return syncid;
  else
    insert into sucursales values(sd, 0);
    return 0;
  end if;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION getlatestsyncedid(character varying) OWNER TO postgres;
