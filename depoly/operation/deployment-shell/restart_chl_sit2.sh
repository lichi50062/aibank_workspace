export NAMESPACE=tpebnknanot
for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'chl' | grep 'sit2')
do
#	kubectl -n $NAMESPACE scale $d --replicas=0
	kubectl -n $NAMESPACE scale $d --replicas=$1
done
