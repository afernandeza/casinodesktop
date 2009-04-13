-- Table: syncinfo

-- DROP TABLE syncinfo;

CREATE TABLE syncinfo
(
  qid integer NOT NULL,
  query text NOT NULL,
  generated timestamp with time zone NOT NULL,
  CONSTRAINT syncinfo_pkey PRIMARY KEY (qid)
)
WITH (OIDS=FALSE);
ALTER TABLE syncinfo OWNER TO casindesktopapp;
