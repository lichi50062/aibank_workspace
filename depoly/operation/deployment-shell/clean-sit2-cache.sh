NAMESPACE=tpebnknanot
ENV=SIT2
if [ "$ENV" == "SIT2" ]; then
  NAMESPACE=tpebnknanot
  EXTRAHEADER='x-aibank-appVersion: sit2-'
  EXTRASVC='-sit2'
elif [ "$ENV" == "UAT" ]; then
  NAMESPACE=tpebnkaibnkt
  EXTRAHEADER="a: b"
  EXTRASVC=''
elif [ "$ENV" == "UAT2" ]; then
  NAMESPACE=tpebnkaibnkt
  EXTRAHEADER='x-aibank-appVersion: uat2-'
  EXTRASVC='-uat2'
elif [ "$ENV" == "PT" ]; then
  NAMESPACE=tpebnkaibkpdt
  EXTRAHEADER="a: b"
  EXTRASVC=''
elif [ "$ENV" == "PT2" ]; then
  NAMESPACE=tpebnkaibkpdt
  EXTRAHEADER='x-aibank-appVersion: 2.0-'
  EXTRASVC='-pt2'
fi

curl http://oauth.${NAMESPACE}.cldatu1.groupt.fbt.com/oauth2/token -u "biz:bizsecret" -d "grant_type=client_credentials&scope=biz" -H "$EXTRAHEADER" -o token.json
token=$(jq -r .access_token token.json)

curl http://gateway.${NAMESPACE}.cldatu1.groupt.fbt.com/svc-biz-system-config/api/v1.0/caches/list -H "Authorization: bearer $token" -H "$EXTRAHEADER" -o list.json

for k in $(jq -r ".data | .[].cacheKey" list.json)
do
  curl -X POST "http://gateway.${NAMESPACE}.cldatu1.groupt.fbt.com/svc-biz-system-config/api/v1.0/caches/$k/delete" -H "Authorization: bearer $token" -H "$EXTRAHEADER" -o -
done


#後台部分cache
curl http://gateway.${NAMESPACE}.cldatu1.groupt.fbt.com/svc-admin-biz${EXTRASVC}/b2eGlobalService/refreshAllCache
curl http://gateway.${NAMESPACE}.cldatu1.groupt.fbt.com/svc-admin-chl${EXTRASVC}/pagecode/commonpage/task/b2etaskcontroller/refreshAllCache
