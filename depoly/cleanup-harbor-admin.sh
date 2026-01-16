#!/bin/sh

for p in $(cat ../aibank-admin/project-list.txt)
do
  echo "process $p"
  curl -k "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/admin%252F$p/artifacts?with_tag=true&with_scan_overview=true&with_label=true&with_accessory=false&page_size=50&page=1" -u "$1:$2" > tmp.json
  for d in $(cat tmp.json | jq -r ".[2:][].digest")
  do
    curl -k -X 'DELETE' "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/admin%252F$p/artifacts/$d" -u "$1:$2"
  done
done
