-- View: employeesdata

-- DROP VIEW employeesdata;

CREATE OR REPLACE VIEW employeesdata AS 
 SELECT empleados.nombres, empleados.appaterno, empleados.apmaterno, empleados.sexo, empleados.fechanac, empleados.foto, empleados.telcasa, empleados.telcel, direcciones.callenum, direcciones.numint, direcciones.colonia, direcciones.municipio, direcciones.codigopostal, direcciones.estado, direcciones.pais, usuarios.usuario, tipoempleados.tipo
   FROM empleados, direcciones, usuarios, tipoempleados
  WHERE empleados.direccionid = direcciones.direccionid AND empleados.usuarioid = usuarios.usuarioid AND empleados.tipoempleadoid = tipoempleados.tipoempleadoid;

ALTER TABLE employeesdata OWNER TO postgres;

