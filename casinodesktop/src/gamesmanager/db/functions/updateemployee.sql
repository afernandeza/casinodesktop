-- Function: updateemployee(character varying, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer)

-- DROP FUNCTION updateemployee(character varying, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer);

CREATE OR REPLACE FUNCTION updateemployee(eid character varying, n character varying, ap character varying, am character varying, s character, fn date, ft bytea, tc character varying, tl character varying, calle character varying, nint character varying, col character varying, mun character varying, cp character varying, edo character varying, ps character varying, pw character varying, tid integer)
  RETURNS boolean AS
$BODY$
declare
  addressid integer;
  userid integer;
begin
  select into addressid direccionid from empleados where empleadoid = eid;
  select into userid usuarioid from empleados where empleadoid = eid;
  update empleados set 
    nombres = n,
    appaterno = ap,
    apmaterno = am,
    sexo = s,
    fechanac = fn,
    foto = ft,
    telcasa = tc,
    telcel = tl,
    tipoempleadoid = tid
    where empleadoid = eid;
  update direcciones set 
    callenum = calle,
    numint = nint,
    colonia = col,
    municipio = mun,
    codigopostal = cp,
    estado = edo,
    pais = ps
    where direccionid = addressid;
  update usuarios set
    "password" = md5(pw)
    where usuarioid = userid;
   return true;
end;
$BODY$
  LANGUAGE 'plpgsql' VOLATILE
  COST 100;
ALTER FUNCTION updateemployee(character varying, character varying, character varying, character varying, character, date, bytea, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, character varying, integer) OWNER TO casindesktopapp;
