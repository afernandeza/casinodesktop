-- Function: findemployee(character varying)

-- DROP FUNCTION findemployee(character varying);

CREATE OR REPLACE FUNCTION findemployee(IN eid character varying, OUT nombres character varying, OUT appaterno character varying, OUT apmaterno character varying, OUT sexo character, OUT fechanac date, OUT foto bytea, OUT telcasa character varying, OUT telcel character varying, OUT callenum character varying, OUT numint character varying, OUT colonia character varying, OUT municipio character varying, OUT codigopostal character varying, OUT estado character varying, OUT pais character varying, OUT usuario character varying, OUT tipoempleado character varying)
  RETURNS record AS
$BODY$
SELECT nombres, appaterno, apmaterno, sexo, fechanac, foto,
telcasa, telcel, callenum, numint, colonia, municipio, codigopostal,
estado, pais, usuario, tipo
FROM empleados, direcciones, usuarios, tipoempleados 
WHERE empleados.empleadoid = $1 AND 
empleados.direccionid = direcciones.direccionid AND
empleados.usuarioid = usuarios.usuarioid AND
empleados.tipoempleadoid = tipoempleados.tipoempleadoid;
$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100;
ALTER FUNCTION findemployee(character varying) OWNER TO postgres;
