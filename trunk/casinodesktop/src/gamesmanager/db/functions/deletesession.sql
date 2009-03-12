CREATE FUNCTION deletesession(IN id integer) RETURNS void AS
$BODY$
execute 'delete from sesiones where sesionid = ' 
|| quote_literal(id);
$BODY$
LANGUAGE 'sql' VOLATILE;
ALTER FUNCTION deletesession(IN integer) OWNER TO casindesktopapp;
