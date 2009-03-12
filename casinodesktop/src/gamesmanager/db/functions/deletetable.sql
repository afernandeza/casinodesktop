CREATE FUNCTION deletetable(IN id character varying) RETURNS void AS
$BODY$
execute 'delete from mesas where mesaid = ' 
|| quote_literal(id);
$BODY$
LANGUAGE 'sql' VOLATILE;
ALTER FUNCTION deletetable(IN character varying) OWNER TO casindesktopapp;
