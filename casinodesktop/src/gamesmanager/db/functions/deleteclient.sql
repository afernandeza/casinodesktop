CREATE FUNCTION deleteclient(IN id character varying) RETURNS void AS
$BODY$
execute 'delete from clientes where clienteid = ' 
|| quote_literal(id);
$BODY$
LANGUAGE 'sql' VOLATILE;
ALTER FUNCTION deleteclient(IN character varying) OWNER TO casindesktopapp;
