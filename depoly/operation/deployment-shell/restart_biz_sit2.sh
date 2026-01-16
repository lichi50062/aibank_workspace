#
# restart_biz_2.sh 0
#
export NAMESPACE=tpebnknanot
replica=$1
for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'biz' | grep 'sit2' )
do
 	# kubectl -n $NAMESPACE scale $d --replicas=0
	kubectl -n $NAMESPACE scale $d --replicas=$replica
done
