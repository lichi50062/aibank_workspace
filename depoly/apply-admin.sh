#!/bin/sh
set -x

IMG_VERSION=$(cat ../adminImageVersion.properties | cut -f2 -d'=')
if [ "$1" == "v2" ]
then
  APP_VER=v2
else
  APP_VER=v1
fi

# apply global settings (pvc, secret)
find global -name '*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply global failed"
    exit 1
fi

# apply config maps 
find admin -name 'cm-*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply cm failed"
    exit 1
fi
# replace image version
find admin -name 'deploy-admin-chl.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > admin/deploy-admin-chl.yml
find admin -name 'deploy-admin-biz.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > admin/deploy-admin-biz.yml

# apply all deployment
find admin -name 'deploy-*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply deploy failed"
    exit 1
fi

# apply all svc
find admin -name 'svc*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply svc failed"
    exit 1
fi

# apply all ingress
find admin -name 'ingress*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply ingress failed"
    exit 1
fi
