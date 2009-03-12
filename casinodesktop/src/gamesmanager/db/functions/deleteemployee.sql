CREATE FUNCTION deleteemployee(IN id character varying) RETURNS void AS
$BODY$
execute 'delete from empleados where empleadoid = ' 
|| quote_literal(id);
$BODY$
LANGUAGE 'sql' VOLATILE;
ALTER FUNCTION deleteemployee(IN character varying) OWNER TO casindesktopapp;
