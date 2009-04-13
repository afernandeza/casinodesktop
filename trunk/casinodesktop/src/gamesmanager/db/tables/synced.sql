-- Table: synced

-- DROP TABLE synced;

CREATE TABLE synced
(
  qid integer NOT NULL,
  sucid character varying(100) NOT NULL,
  CONSTRAINT synced_pkey PRIMARY KEY (qid, sucid)
)
WITH (OIDS=FALSE);
ALTER TABLE synced OWNER TO postgres;
