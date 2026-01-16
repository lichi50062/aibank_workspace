#!/bin/sh
set -x

IMG_VERSION=$(cat ../imageVersion.properties | cut -f2 -d'=')
APP_VER=v1

# generate chl deployment & services
cd chl
for f in $(cat ../../aibank-ms/project-list.txt|grep channel)
do
  a=$(echo $f | sed -e 's/channel-aibank-//');
  cat template/deploy-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > deploy-chl-$a.yml
  cat template/svc-chl.yml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > svc-chl-$a.yml
  cat template/hpa-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > hpa-chl-$a.yml
  cat template/destination-rule-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > dr-chl-$a.yml
  cat template/virtual-service-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%v2target%/$V2TARGET/" > vs-chl-$a.yml
done
cd ..

#generate biz deployment & services
cd biz
for f in $(cat ../../aibank-ms/project-list.txt|grep service)
do
  a=$(echo $f | sed -e 's/service-aibank-//');
  cat template/deploy-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > deploy-biz-$a.yml
  cat template/svc-biz.yml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > svc-biz-$a.yml
  cat template/hpa-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > hpa-biz-$a.yml
  cat template/destination-rule-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > dr-biz-$a.yml
  cat template/virtual-service-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%v2target%/$V2TARGET/" > vs-biz-$a.yml
done
cd ..

# change gateway/oauth version
find servers -name 'deploy-gateway.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/"  > servers/gateway/deploy-gateway.yml
find servers -name 'hpa-gateway.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/"  > servers/gateway/hpa-gateway.yml
find servers -name 'deploy-oauth.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > servers/oauth/deploy-oauth.yml
find servers -name 'hpa-oauth.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > servers/oauth/hpa-oauth.yml
find batch -name 'deploy-batch.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > batch/deploy-batch.yml

if [ "$1" == "-n" ]
then
  exit 0
fi

# apply global settings (pvc, secret)
find global -name '*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply global failed"
    exit 1
fi

# apply config maps 
find batch biz chl infra ixtein servers tools -name 'cm-*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply cm failed"
    exit 1
fi

# apply all deployment
find batch biz chl infra ixtein servers tools -name 'deploy*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply svc failed"
    exit 1
fi

# apply all svc
find batch biz chl infra ixtein servers tools -name 'svc*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply svc failed"
    exit 1
fi

# apply all statefulset
find batch biz chl infra ixtein servers tools -name 'statefulset*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply statefulset failed"
    exit 1
fi

# apply all ingress
find batch biz chl infra ixtein servers tools -name 'ingress*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply ingress failed"
    exit 1
fi

# apply all hpa
find biz chl ixtein servers -name 'hpa*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply hpa failed"
    exit 1
fi
