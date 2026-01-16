read -p "input app header version <imp-app-version>-<detail version>: " target
export TARGET=$target

NAMESPACE=tpebnknanot
EXTRAHEADER="x-aibank-appVersion: $TARGET"

curl http://oauth.$NAMESPACE.cldatu1.groupt.fbt.com/oauth2/token -u "biz:bizsecret" -d "grant_type=client_credentials&scope=biz" -H "$EXTRAHEADER" -o token.json
token=$(jq -r .access_token token.json)

curl -X POST "http://gateway.${NAMESPACE}.cldatu1.groupt.fbt.com/svc-batch/api/v1.0/scheduler-jobs/running-program/list" -H "Authorization: bearer $token" -H "$EXTRAHEADER" -o -
