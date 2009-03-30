-- View: employeesdata

-- DROP VIEW employeesdata;

CREATE OR REPLACE VIEW employeesdata AS 
 SELECT empleados.empleadoid, empleados.nombres, empleados.appaterno, empleados.apmaterno, empleados.sexo, empleados.fechanac, empleados.foto, empleados.telcasa, empleados.telcel, empleados.fechareg, empleados.despedido, direcciones.direccionid, direcciones.callenum, direcciones.numint, direcciones.colonia, direcciones.municipio, direcciones.codigopostal, direcciones.estado, direcciones.pais, usuarios.usuarioid, usuarios.usuario, usuarios.password, tipoempleados.tipoempleadoid, tipoempleados.tipo
   FROM empleados, direcciones, usuarios, tipoempleados
  WHERE empleados.direccionid = direcciones.direccionid AND 
  empleados.usuarioid = usuarios.usuarioid AND 
  empleados.tipoempleadoid = tipoempleados.tipoempleadoid
  ORDER BY empleados.appaterno, empleados.apmaterno, empleados.nombres;

ALTER TABLE employeesdata OWNER TO casindesktopapp;

