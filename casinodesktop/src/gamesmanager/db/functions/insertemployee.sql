-- Function: insertemployee(character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer)

-- DROP FUNCTION insertemployee(character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer);

CREATE OR REPLACE FUNCTION insertemployee(nombres character varying, appaterno character varying, apmaterno character varying, sexo character, fechanac date, foto bytea, telcasa character varying, telcel character varying, callenum character varying, numint character varying, colonia character varying, municipio character varying, codigopostal character varying, estado character varying, pais character varying, usuario character varying, passwd character varying, tipoempleadoid integer)
  RETURNS void AS
$BODY$
declare
  addressid integer;
  employeeid character varying;
  userid integer;
begin
  select into employeeid employeeidgenerator from employeeidgenerator();
  select into addressid addressauto from nextval('addressid') as addressauto;
  select into userid usidauto from nextval('userid') as usidauto;
  insert into empleados values(addressid, nombres, appaterno, apmaterno,
  sexo, fechanac, foto, userid, tipoempleadoid, telcasa, telcel, employeeid, current_date);
  insert into direcciones values(addressid, callenum, numint, colonia,
  municipio, codigopostal, estado, pais);
  insert into usuarios values(userid, usuario, passwd);
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertemployee(character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer) OWNER TO postgres;
