-- Function: insertemployee(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)

-- DROP FUNCTION insertemployee(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION insertemployee(nombres character varying,
 appaterno character varying, apmaterno character varying, sexo character, 
 fechanac date, foto bytea, telcasa character varying, telcel character varying, 
 callenum character varying, numint character varying, colonia character varying, 
 municipio character varying, codigopostal character varying, 
 estado character varying, pais character varying, usuario character varying, 
 passwd character varying, tipoempleadoid integer)
  RETURNS boolean AS
$BODY$
declare
  addressid integer;
  employeeid integer;
  userid integer;
  success boolean;
begin
  select into employeeid empidauto from nextval('employeeid') as empidauto;
  select into addressid addressauto from nextval('addressid') as addressauto;
  select into userid usidauto from nextval('userid') as usidauto;
  insert into empleados values(addressid, nombres, appaterno, apmaterno,
  sexo, fechanac, foto, userid, tipoempleadoid, telcasa, telcel, employeeid, current_date);
  insert into direcciones values(addressid, callenum, numint, colonia,
  municipio, codigopostal, estado, pais);
  insert into usuarios values(userid, usuario, passwd);
  success := true;
  return success;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;

-- Function: insertemployee(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)

-- DROP FUNCTION insertemployee(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION insertemployee(credito numeric, nombres character varying,
 appaterno character varying, apmaterno character varying, sexo character, 
 fechanac date, foto bytea, telcasa character varying, telcel character varying, 
 callenum character varying, numint character varying, colonia character varying, 
 municipio character varying, codigopostal character varying, 
 estado character varying, pais character varying)
  RETURNS boolean AS
$BODY$
declare
  addressid integer;
  employeeid integer;
  success boolean;
begin
  select into employeeid empidauto from nextval('employeeid') as empidauto;
  select into addressid addressauto from nextval('addressid') as addressauto;
  insert into empleados values(addressid, credito, nombres, appaterno, apmaterno,
  sexo, fechanac, foto, current_date, telcasa, telcel, clientid);
  insert into direcciones values(addressid, callenum, numint, colonia,
  municipio, codigopostal, estado, pais);
  success := true;
  return success;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertemployee(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying) OWNER TO postgres;
