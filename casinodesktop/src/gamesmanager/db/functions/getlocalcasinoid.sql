-- Function: getlocalcasinoid()

-- DROP FUNCTION getlocalcasinoid();

CREATE OR REPLACE FUNCTION getlocalcasinoid()
  RETURNS character varying AS
$BODY$begin
return 'SUCA';
end;$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION getlocalcasinoid() OWNER TO casinomngmtapp;
