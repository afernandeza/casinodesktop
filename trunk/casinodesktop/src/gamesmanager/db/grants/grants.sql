grant select on clientes to casinomngmtapp;
grant select on empleados to casinomngmtapp;
grant select on mesas to casinomngmtapp;
grant select on sesiones to casinomngmtapp;
grant select on tipoempleados to casinomngmtapp;
grant select on tiposjuego to casinomngmtapp;
grant select on sesiones to casinomngmtapp;
grant select on mesasinfo to casinomngmtapp;
grant select on repmesas to casinomngmtapp;
grant select on repclientes to casinomngmtapp;
grant select on repempleados to casinomngmtapp;
grant execute on function repsesiones(date, date) to casinomngmtapp;
grant execute on function deleteextuser(character varying);
grant execute on function  insertextuser(character varying, character varying);
grant execute on function  updateextuser(character varying, character varying);


