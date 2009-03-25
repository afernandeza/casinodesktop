-- Function: insertclient(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)

-- DROP FUNCTION insertclient(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION insertclient(credito numeric, nombres character varying, appaterno character varying, apmaterno character varying, sexo character, fechanac date, foto bytea, telcasa character varying, telcel character varying, callenum character varying, numint character varying, colonia character varying, municipio character varying, codigopostal character varying, estado character varying, pais character varying)
  RETURNS VOID AS
$BODY$
declare
  addressid integer;
  clientid varchar;
begin
  select into clientid clientidgenerator from clientidgenerator();
  select into addressid addressauto from nextval('addressid') as addressauto;
  insert into clientes values(addressid, credito, nombres, appaterno, apmaterno,
  sexo, fechanac, foto, current_date, telcasa, telcel, clientid);
  insert into direcciones values(addressid, callenum, numint, colonia,
  municipio, codigopostal, estado, pais);
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION insertclient(numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying) OWNER TO postgres;
