export NAMESPACE=tpebnknanot
NODE_NAME="$1"

for pod in $(kubectl -n $NAMESPACE get pod | awk 'NR>1 {print $1 "," $3}' | grep 'biz')
do
        IFS=, read -r pod_name pod_status <<< "$pod"
        deployment_name=$(echo "$pod_name" | cut -d'-' -f1-4)
        echo $deployment_name
        echo $pod_name
        echo $pod_status
        if [ "$pod_status" != "Running" ]; then
                kubectl -n $NAMESPACE patch deploy $deployment_name --patch "$(cat <<EOF
{
  "spec": {
    "template": {
      "spec": {
        "affinity": {
          "nodeAffinity": {
            "requiredDuringSchedulingIgnoredDuringExecution": {
              "nodeSelectorTerms": [
                {
                  "matchExpressions": [
                    {
                      "key": "kubernetes.io/hostname",
                      "operator": "NotIn",
                      "values": [
                        "$NODE_NAME"
                      ]
                    }
                  ]
                }
              ]
            }
          }
        }
      }
    }
  }
}
EOF
)"
        else
                echo "Pod $pod_name is running."
        fi
done
for pod in $(kubectl -n $NAMESPACE get pod | awk 'NR>1 {print $1 "," $3}' | grep 'chl')
do
        IFS=, read -r pod_name pod_status <<< "$pod"
        deployment_name=$(echo "$pod_name" | cut -d'-' -f1-4)
        echo $deployment_name
        echo $pod_name
        echo $pod_status
        if [ "$pod_status" != "Running" ]; then
                kubectl -n $NAMESPACE patch deploy $deployment_name --patch "$(cat <<EOF
{
  "spec": {
    "template": {
      "spec": {
        "affinity": {
          "nodeAffinity": {
            "requiredDuringSchedulingIgnoredDuringExecution": {
              "nodeSelectorTerms": [
                {
                  "matchExpressions": [
                    {
                      "key": "kubernetes.io/hostname",
                      "operator": "NotIn",
                      "values": [
                        "$NODE_NAME"
                      ]
                    }
                  ]
                }
              ]
            }
          }
        }
      }
    }
  }
}
EOF
)"
        else
                echo "Pod $pod_name is running."
        fi
done
