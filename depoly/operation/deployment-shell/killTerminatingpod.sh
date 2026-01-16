export NAMESPACE=tpebnknanot

for pod in $(kubectl -n $NAMESPACE get pod | awk 'NR>1 {print $1 "," $3}')

do
        IFS=, read -r pod_name pod_status <<< "$pod"
	deployment_name=$(echo "$pod_name" | cut -d'-' -f1-4)
	echo $deployment_name
        echo $pod_name
        echo $pod_status
	if [ "$pod_status" = "Terminating" ]; then
  	  kubectl -n $NAMESPACE delete pod $pod_name --force
    	fi
done
