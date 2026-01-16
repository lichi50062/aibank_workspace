#!/bin/sh
for d in $(kubectl -n ${NAMESPACE} get deploy -o name|grep -E "(chl-|biz-)")
do
  kubectl -n ${NAMESPACE} delete $d
done

