#!/bin/sh

for p in $(cat ../aibank-ms/project-list.txt | grep server)
do
  echo "process $p"
  curl -k "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/server%252F$p/artifacts?with_tag=true&with_scan_overview=true&with_label=true&with_accessory=false&page_size=50&page=1" -u "$1:$2" > tmp.json
  for d in $(cat tmp.json | jq -r ".[2:][].digest")
  do
    curl -k -X 'DELETE' "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/server%252F$p/artifacts/$d" -u "$1:$2"
  done
done

for p in $(cat ../aibank-ms/project-list.txt | grep channel)
do
  echo "process $p"
  curl -k "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/channel%252F$p/artifacts?with_tag=true&with_scan_overview=true&with_label=true&with_accessory=false&page_size=50&page=1" -u "$1:$2" > tmp.json
  for d in $(cat tmp.json | jq -r ".[2:][].digest")
  do
    curl -k -X 'DELETE' "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/channel%252F$p/artifacts/$d" -u "$1:$2"
  done
done

for p in $(cat ../aibank-ms/project-list.txt | grep service)
do
  echo "process $p"
  curl -k "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/business%252F$p/artifacts?with_tag=true&with_scan_overview=true&with_label=true&with_accessory=false&page_size=50&page=1" -u "$1:$2" > tmp.json
  for d in $(cat tmp.json | jq -r ".[2:][].digest")
  do
    curl -k -X 'DELETE' "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/business%252F$p/artifacts/$d" -u "$1:$2"
  done
done

for p in $(cat ../aibank-ms/project-list.txt | grep batch)
do
  echo "process $p"
  curl -k "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/batch%252F$p/artifacts?with_tag=true&with_scan_overview=true&with_label=true&with_accessory=false&page_size=50&page=1" -u "$1:$2" > tmp.json
  for d in $(cat tmp.json | jq -r ".[2:][].digest")
  do
    curl -k -X 'DELETE' "https://registry.groupt.fbt.com/api/v2.0/projects/$NAMESPACE/repositories/batch%252F$p/artifacts/$d" -u "$1:$2"
  done
done
