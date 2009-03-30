-- View: clientsdata

-- DROP VIEW clientsdata;

CREATE OR REPLACE VIEW clientsdata AS 
 SELECT clientes.clienteid, clientes.nombres, clientes.appaterno, clientes.apmaterno, clientes.sexo, clientes.fechanac, clientes.foto, clientes.telcasa, clientes.telcel, clientes.fechareg, direcciones.direccionid, direcciones.callenum, direcciones.numint, direcciones.colonia, direcciones.municipio, direcciones.codigopostal, direcciones.estado, direcciones.pais, clientes.credito
   FROM clientes, direcciones
  WHERE clientes.direccionid = direcciones.direccionid
  ORDER BY clientes.appaterno, clientes.apmaterno, clientes.nombres;

ALTER TABLE clientsdata OWNER TO casindesktopapp;

