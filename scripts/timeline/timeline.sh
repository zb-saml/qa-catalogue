#!/usr/bin/env bash

. $(dirname $0)/../../setdir.sh

HISTORICAL=${BASE_OUTPUT_DIR}/_historical/${NAME}
if [[ ! -d ${HISTORICAL} ]]; then
  mkdir -p ${HISTORICAL}
fi

echo $HISTORICAL

CSVS="count.csv issue-total.csv issue-by-category.csv issue-by-type.csv"
for CSV_FILE in CSVS; do
  if [[ -e ${HISTORICAL}/${CSV_FILE} ]]; then
    rm ${HISTORICAL}/${CSV_FILE}
  fi
done

echo "version,total,processed" > ${HISTORICAL}/count.csv
echo "version,type,instances,records" > ${HISTORICAL}/issue-total.csv
echo "version,id,category,instances,records" > ${HISTORICAL}/issue-by-category.csv
echo "version,id,categoryId,category,type,instances,records" > ${HISTORICAL}/issue-by-type.csv
for DIR in $(ls ${HISTORICAL}); do
  if [[ -d ${HISTORICAL}/$DIR ]]; then
    echo $DIR,$(grep -v 'total' ${HISTORICAL}/$DIR/count.csv) >> ${HISTORICAL}/count.csv; 
    grep -v 'type,' ${HISTORICAL}/$DIR/issue-total.csv | sed "s;^;$DIR,;" >> ${HISTORICAL}/issue-total.csv; 
    if [[ -f ${HISTORICAL}/$DIR/issue-by-category.csv.gz ]]; then
      gunzip ${HISTORICAL}/$DIR/issue-by-category.csv.gz
    fi
    grep -v 'id,category,instances,records' ${HISTORICAL}/$DIR/issue-by-category.csv | sed "s;^;$DIR,;" >> ${HISTORICAL}/issue-by-category.csv ; 
    if [[ -f ${HISTORICAL}/$DIR/issue-by-type.csv.gz ]]; then
      gunzip ${HISTORICAL}/$DIR/issue-by-type.csv.gz
    fi
    grep -v 'id,categoryId,category,type,instances,records' ${HISTORICAL}/$DIR/issue-by-type.csv | sed "s;^;$DIR,;" >> ${HISTORICAL}/issue-by-type.csv ; 
  fi
done

if [[ -f ${HISTORICAL}/history.sqlite ]]; then
  rm ${HISTORICAL}/history.sqlite
fi
sqlite3 ${HISTORICAL}/history.sqlite << EOF
.mode csv
.import ${HISTORICAL}/count.csv count
.import ${HISTORICAL}/issue-total.csv issue_total
.import ${HISTORICAL}/issue-by-category.csv issue_category
.import ${HISTORICAL}/issue-by-type.csv issue_type
EOF

sqlite3 ${HISTORICAL}/history.sqlite << EOF
.headers on
.separator ,
.output ${HISTORICAL}/timeline-by-category.csv
SELECT id, category, version, ROUND((records * 1.0 / processed) * 100, 2) AS percent 
  FROM issue_category 
  JOIN count USING(version) 
  ORDER BY id, version;
EOF
