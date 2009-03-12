CREATE FUNCTION deletegametype(IN id integer) RETURNS void AS
$BODY$
execute 'delete from tiposjuego where tipojuegoid = ' 
|| quote_literal(id);
$BODY$
LANGUAGE 'sql' VOLATILE;
ALTER FUNCTION deletegametype(IN integer) OWNER TO casindesktopapp;
