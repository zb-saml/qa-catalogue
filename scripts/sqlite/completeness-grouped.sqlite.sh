#!/usr/bin/env bash
#
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Strores completeness-grouped-marc-elements.csv into SQLite
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#

log() {
  timestamp=`date +"%F %T"`
  echo -en "\033[0D\033[1;37m$timestamp>\033[0m "
  echo "$1"
}

OUTPUT_DIR=$1

log "create table marc_elements_grouped"
sqlite3 ${OUTPUT_DIR}/qa_catalogue.sqlite << EOF
CREATE TABLE IF NOT EXISTS "marc_elements_grouped" (
  "groupId"             INTEGER,
  "documenttype"        TEXT,
  "path"                TEXT,
  "packageid"           INTEGER,
  "package"             TEXT,
  "tag"                 TEXT,
  "subfield"            TEXT,
  "number-of-record"    INTEGER,
  "number-of-instances" INTEGER,
  "min"                 INTEGER,
  "max"                 INTEGER,
  "mean"                REAL,
  "stddev"              REAL,
  "histogram"           TEXT
);
CREATE INDEX IF NOT EXISTS "meg_groupId" ON "marc_elements_grouped" ("groupId");
CREATE INDEX IF NOT EXISTS "meg_documenttype" ON "marc_elements_grouped" ("documenttype");
EOF

log "clean marc_elements_grouped"
sqlite3 ${OUTPUT_DIR}/qa_catalogue.sqlite << EOF
DELETE FROM marc_elements_grouped;
EOF

log "create headless CSV"
tail -n +2 ${OUTPUT_DIR}/completeness-grouped-marc-elements.csv > ${OUTPUT_DIR}/completeness-grouped-marc-elements-noheader.csv

log "import marc elements"
sqlite3 ${OUTPUT_DIR}/qa_catalogue.sqlite << EOF
.mode csv
.import ${OUTPUT_DIR}/completeness-grouped-marc-elements-noheader.csv marc_elements_grouped
EOF

log "drop headless CSV"
rm ${OUTPUT_DIR}/completeness-grouped-marc-elements-noheader.csv