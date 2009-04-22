-- Function: syncinfofor(integer)

-- DROP FUNCTION syncinfofor(integer);

CREATE OR REPLACE FUNCTION syncinfofor(latestqid integer)
  RETURNS SETOF syncinfo AS
$BODY$
SELECT *
  FROM syncinfo
WHERE qid > $1
 ORDER BY qid;

$BODY$
  LANGUAGE 'sql' VOLATILE
  COST 100
  ROWS 1000;
ALTER FUNCTION syncinfofor(integer) OWNER TO casindesktopapp;
