-- Function: updateclient(character varying, numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying)

-- DROP FUNCTION updateclient(character varying, numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying);

CREATE OR REPLACE FUNCTION updateclient(cid character varying, cred numeric, n character varying, ap character varying, am character varying, s character, fn date, ft bytea, tc character varying, tl character varying, calle character varying, nint character varying, col character varying, 
mun character varying, cp character varying, edo character varying, ps character varying)
  RETURNS VOID AS
$BODY$
declare
  addressid integer;
begin
  select into addressid direccionid from clientes where clienteid = cid;
  update clientes set 
    credito = cred,
    nombres = n,
    appaterno = ap,
    apmaterno = am,
    sexo = s,
    fechanac = fn,
    foto = ft,
    telcasa = tc,
    telcel = tl
    where clienteid = cid;
  update direcciones set 
    callenum = calle,
    numint = nint,
    colonia = col,
    municipio = mun,
    codigopostal = cp,
    estado = edo,
    pais = ps
    where direccionid = addressid;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updateclient(character varying, numeric, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying) OWNER TO postgres;
