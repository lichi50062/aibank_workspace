#!/bin/sh
IMG_VERSION=$(cat ../imageVersion.properties | cut -f2 -d'=')
APP_VER=v2
if [ -z "$1" ]
then
  echo "v2 target is required, formst: <imp-app-version>-<detail version>, eg: 1.0-20240703142401000"
  exit 1;
fi
V2TARGET=$1

# generate chl deployment & services
cd chl
for f in $(cat ../../aibank-ms/project-list.txt|grep channel)
do
  a=$(echo $f | sed -e 's/channel-aibank-//');
  cat template/virtual-service-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%v2target%/$V2TARGET/" > vs-chl-$a.yml
done
cd ..

#generate biz deployment & services
cd biz
for f in $(cat ../../aibank-ms/project-list.txt|grep service)
do
  a=$(echo $f | sed -e 's/service-aibank-//');
  cat template/virtual-service-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%v2target%/$V2TARGET/" > vs-biz-$a.yml
done
cd ..

# apply biz/chl deployment
find biz chl -name 'vs*.yml' -exec kubectl apply -n ${NAMESPACE} -f {} \;

