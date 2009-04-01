-- Index: usernameindex

-- DROP INDEX usernameindex;

CREATE INDEX usernameindex
  ON usuarios
  USING btree
  (usuario);
