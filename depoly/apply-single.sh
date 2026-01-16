#!/bin/sh
set -x

IMG_VERSION=$1

# apply global settings (pvc, secret)
#find global -name '*.yml' -exec kubectl apply -n ${NAMESPACE} -f {} \;

# apply config maps 
#find batch biz chl infra ixtein servers tools -name 'cm-*.yml' -exec kubectl apply -n ${NAMESPACE} -f {} \;

# generate chl deployment & services
cd chl
for f in $(cat ../../aibank-ms/project-list.txt| grep channel |grep -E "^$2$")
do
  a=$(echo $f | sed -e 's/channel-aibank-//');
  cat template/deploy-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > deploy-chl-$a.yml
  cat template/svc-chl.yml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > svc-chl-$a.yml
  cat template/hpa-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > hpa-chl-$a.yml
  cat template/destination-rule-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > dr-chl-$a.yml
  cat template/virtual-service-chl.yaml.template | sed -e "s/%appname%/$a/" -e "s/%v2target%/$V2TARGET/" > vs-chl-$a.yml
done
cd ..

find chl -name 'deploy*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply deploy failed"
    exit 1
fi

#generate biz deployment & services
cd biz
for f in $(cat ../../aibank-ms/project-list.txt| grep service |grep -E "^$2$")
do
  a=$(echo $f | sed -e 's/service-aibank-//');
  cat template/deploy-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > deploy-biz-$a.yml
  cat template/svc-biz.yml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > svc-biz-$a.yml
  cat template/hpa-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" > hpa-biz-$a.yml
  cat template/destination-rule-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%projname%/$f:$IMG_VERSION/" -e "s/%appVer%/$APP_VER/" > dr-biz-$a.yml
  cat template/virtual-service-biz.yaml.template | sed -e "s/%appname%/$a/" -e "s/%v2target%/$V2TARGET/" > vs-biz-$a.yml
done
cd ..

find biz -name 'deploy*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply deploy failed"
    exit 1
fi

# change gateway/oauth version
if [ "$2" == "gateway-server" ]; then
  find servers -name 'deploy-gateway.yml' -exec sed -i'.bak' -e "s/%imageVersion%/$IMG_VERSION/" {} \; 
  find servers -name 'deploy*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
  if [ $? -ne 0 ]; then
      echo "apply deploy failed"
      exit 1
  fi
fi
if [ "$2" == "oauth-server" ]; then
  find servers -name 'deploy-oauth.yml' -exec sed -i'.bak' -e "s/%imageVersion%/$IMG_VERSION/" {} \; 
  find servers -name 'deploy*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
  if [ $? -ne 0 ]; then
      echo "apply deploy failed"
      exit 1
  fi
fi
if [ "$2" == "aibank-batch" ]; then
  find batch -name 'deploy-batch.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > batch/deploy-batch.yml
  find batch -name 'deploy*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
  if [ $? -ne 0 ]; then
      echo "apply deploy failed"
      exit 1
  fi

fi
