export NAMESPACE=tpebnknanot
for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'biz' )
do
#	kubectl -n $NAMESPACE scale $d --replicas=0
	kubectl -n $NAMESPACE scale $d --replicas=0
done
for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'chl' )
do
#	kubectl -n $NAMESPACE scale $d --replicas=0
	kubectl -n $NAMESPACE scale $d --replicas=0
done
echo "20 秒繼續處理..."
sleep 20
echo "20 秒結束繼續處理..."

for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'biz' )
do
#       kubectl -n $NAMESPACE scale $d --replicas=0
        kubectl -n $NAMESPACE scale $d --replicas=1
done

echo "10 秒繼續處理..."
sleep 10
echo "10 秒結束繼續處理..."

for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'chl' | grep 'sit2')
do
#       kubectl -n $NAMESPACE scale $d --replicas=0
        kubectl -n $NAMESPACE scale $d --replicas=1
done
echo "10 秒繼續處理..."
sleep 10
echo "10 秒結束繼續處理..."

for d in $(kubectl -n $NAMESPACE get deploy -o name | grep 'chl' | grep 'v1')
do
#       kubectl -n $NAMESPACE scale $d --replicas=0
       # kubectl -n $NAMESPACE scale $d --replicas=1 不起 chl1
done
