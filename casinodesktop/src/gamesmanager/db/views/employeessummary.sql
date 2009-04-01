-- View: employeessummary

-- DROP VIEW employeessummary;

CREATE OR REPLACE VIEW employeessummary AS 
 SELECT employeesdata.empleadoid, employeesdata.tipo, (((employeesdata.appaterno::text || ' '::text) || employeesdata.apmaterno::text) || ' '::text) || employeesdata.nombres::text AS nombre, employeesdata.usuario, employeesdata.active, employeesdata.fechareg, employeesdata.telcasa, employeesdata.telcel, employeesdata.despedido
   FROM employeesdata;

ALTER TABLE employeessummary OWNER TO casindesktopapp;

