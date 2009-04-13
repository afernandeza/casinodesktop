-- Function: clienttype(character varying)

-- DROP FUNCTION clienttype(character varying);

CREATE OR REPLACE FUNCTION clienttype(cid character varying)
  RETURNS integer AS
$BODY$
declare
  clientelocal integer;
  clienteexterno integer;
BEGIN
  select into clientelocal count(*) from clientsdata where clienteid = $1;
  select into clienteexterno count(*) from clientesexternos where clienteid = $1;
  
  if clientelocal = 1 then 
    return 1;
  else 
    if clienteexterno = 1 then
      return 2;
    end if;
  return 3;
  end if;
END;

$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION clienttype(character varying) OWNER TO casindesktopapp;
