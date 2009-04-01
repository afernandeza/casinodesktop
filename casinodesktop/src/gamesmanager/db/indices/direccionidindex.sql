-- Index: direccionidindex

-- DROP INDEX direccionidindex;

CREATE INDEX direccionidindex
  ON direcciones
  USING btree
  (direccionid);
