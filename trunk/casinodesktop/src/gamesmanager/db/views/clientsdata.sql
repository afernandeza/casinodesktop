-- View: clientsdata

-- DROP VIEW clientsdata;

CREATE OR REPLACE VIEW clientsdata AS 
 SELECT clientes.appaterno
   FROM clientes, direcciones
  WHERE clientes.direccionid = direcciones.direccionid;

ALTER TABLE clientsdata OWNER TO casindesktopapp;

