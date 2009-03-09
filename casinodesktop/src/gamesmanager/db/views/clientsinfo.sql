-- View: clientsinfo

-- DROP VIEW clientsinfo;

CREATE OR REPLACE VIEW clientsinfo AS 
 SELECT clientes.appaterno
   FROM clientes, direcciones
  WHERE clientes.direccionid = direcciones.direccionid;

ALTER TABLE clientsinfo OWNER TO postgres;

