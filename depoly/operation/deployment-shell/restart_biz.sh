export NAMESPACE=tpebnknanot
for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'biz' | grep 'v1' )
do
	kubectl -n $NAMESPACE scale $d --replicas=$1
	# kubectl -n $NAMESPACE scale $d --replicas=1
done
