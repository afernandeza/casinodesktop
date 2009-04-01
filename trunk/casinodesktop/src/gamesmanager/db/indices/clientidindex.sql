-- Index: clientidindex

-- DROP INDEX clientidindex;

CREATE INDEX clientidindex
  ON clientes
  USING btree
  (clienteid);
