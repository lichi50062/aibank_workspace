#!/bin/sh
NAMESPACE=tpebnkaibkpdt


for d in $(kubectl -n $NAMESPACE get virtualservice -o name | grep -E "biz|chl")
do
  kubectl -n $NAMESPACE delete $d
done

for d in $(kubectl -n $NAMESPACE get destinationrule -o name | grep -E "biz|chl")
do
  kubectl -n $NAMESPACE delete $d
done

echo "sleep 30 secs for istio proxy sync"
sleep 30

for d in $(kubectl -n $NAMESPACE get deploy -o name | grep -- "-v2")
do
  kubectl -n $NAMESPACE delete $d
done

