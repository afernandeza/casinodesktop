-- Index: empleadoidindex

-- DROP INDEX empleadoidindex;

CREATE INDEX empleadoidindex
  ON empleados
  USING btree
  (empleadoid);
