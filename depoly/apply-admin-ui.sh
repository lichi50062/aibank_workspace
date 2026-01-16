#!/bin/sh
set -x

IMG_VERSION=$(cat ../adminUIImageVersion.properties | cut -f2 -d'=')

# apply all deployment
find admin-ui -name 'cm-*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply cm failed"
    exit 1
fi

# replace image version
find admin-ui -name 'deploy-admin-frontend.yml.template' -exec cat {} \; | sed -e "s/%imageVersion%/$IMG_VERSION/" > admin-ui/deploy-admin-frontend.yml

# apply all deployment
find admin-ui -name 'deploy-*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply deploy failed"
    exit 1
fi
# apply all svc
find admin-ui -name 'svc*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply svc failed"
    exit 1
fi
# apply all ingress
find admin-ui -name 'ingress*.yml' -print0 | xargs -0 -I {} kubectl apply -n ${NAMESPACE} -f {}
if [ $? -ne 0 ]; then
    echo "apply ingress failed"
    exit 1
fi
