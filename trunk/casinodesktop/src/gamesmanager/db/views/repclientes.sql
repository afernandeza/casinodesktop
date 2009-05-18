-- View: repclientes

-- DROP VIEW repclientes;

CREATE OR REPLACE VIEW repclientes AS 
 SELECT clientes.nombres, clientes.appaterno, clientes.apmaterno, clientes.fechareg, clientes.credito
   FROM clientes;

ALTER TABLE repclientes OWNER TO casinomngmtapp;

