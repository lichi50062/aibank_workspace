package com.tfb.aibank.biz.security.services.fido2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.security.repository.MbDeviceInfoRepository;
import com.tfb.aibank.biz.security.repository.aib.MbDevicePushInfoRepository;
import com.tfb.aibank.biz.security.repository.aib.PushSubscriptionRepository;
import com.tfb.aibank.biz.security.repository.aib.entities.MbDevicePushInfoEntity;
import com.tfb.aibank.biz.security.repository.aib.entities.PushSubscriptionEntity;
import com.tfb.aibank.biz.security.repository.entities.MbDeviceInfoEntity;
import com.tfb.aibank.biz.security.services.fido2.model.RemoveUserFido2Request;
import com.tfb.aibank.biz.security.services.fido2.model.RemoveUserFido2Response;
import com.tfb.aibank.biz.security.services.fido2.model.Fido2PushNotifyRequest;
import com.tfb.aibank.biz.security.services.fido2.model.Fido2PushNotifyResponse;
import com.tfb.aibank.biz.security.services.fido2.model.QueryUserInfoFido2Request;
import com.tfb.aibank.biz.security.services.fido2.model.QueryUserInfoFido2Response;
import com.tfb.aibank.biz.security.services.fido2.model.UpdateCreditCardFido2FlagRequest;
import com.tfb.aibank.common.error.AIBankErrorCode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.message.BasicHeader;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Fido2Service {

    private static final IBLog logger = IBLog.getLog(Fido2Service.class);

    private IdentityService identityService;

    private MbDeviceInfoRepository mbDeviceInfoRepository;

    private MbDevicePushInfoRepository mbDevicePushInfoRepository;

    private PushSubscriptionRepository pushSubscriptionRepository;

    private SystemParamCacheManager systemParamCacheManager;

    private static final String HTTP_CONTENTTYPE_JSON_UTF8 = "application/json; charset=utf-8";

    public Fido2Service(IdentityService identityService, MbDeviceInfoRepository mbDeviceInfoRepository, MbDevicePushInfoRepository mbDevicePushInfoRepository, PushSubscriptionRepository pushSubscriptionRepository, SystemParamCacheManager systemParamCacheManager) {
        this.identityService = identityService;
        this.mbDeviceInfoRepository = mbDeviceInfoRepository;
        this.mbDevicePushInfoRepository = mbDevicePushInfoRepository;
        this.pushSubscriptionRepository = pushSubscriptionRepository;
        this.systemParamCacheManager = systemParamCacheManager;
    }

    // [FIDO2] FIDO2 API 查詢會員狀態
    public QueryUserInfoFido2Response queryUserInfoFido2(QueryUserInfoFido2Request request) throws ActionException {
        try {
            logger.info("==================== queryUserInfoFido2 start  ====================");

            ObjectMapper mapper = new ObjectMapper();
            logger.info("queryUserInfoFido2 request: {}", request);
            String jsonBody = mapper.writeValueAsString(request);

            String apiKey = systemParamCacheManager.getValue("AIBANK", "FIDO2_HEADER_API_KEY");
            String domain = systemParamCacheManager.getValue("AIBANK", "FIDO2_API_DOMAIN_URL");
            String path = systemParamCacheManager.getValue("AIBANK", "FIDO2_API_QUERY");

            String url = domain + path;
            logger.info("queryUserInfoFido2 apiKey: {}, domain: {}, path: {}, url: {}", apiKey, domain, path, url);
            List<Header> header = Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, HTTP_CONTENTTYPE_JSON_UTF8),
                    new BasicHeader("ApiKey", apiKey));

            CustomHttpResponse response = HttpClient5.doPost(url, header, jsonBody);
            logger.info("queryUserInfoFido2 response: {}", response.getResponse());

            QueryUserInfoFido2Response queryUserInfoFido2Response = mapper.readValue(response.getResponse(), QueryUserInfoFido2Response.class);

            logger.info("==================== queryUserInfoFido2 end  ====================");
            return queryUserInfoFido2Response;
        }catch (Exception e){
            logger.error("FIDO2 API 查詢會員狀態 錯誤", e.getMessage());
            throw new ActionException(CommonErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    // [FIDO2] FIDO2 API 使用者註銷
    public RemoveUserFido2Response removeUserFido2(RemoveUserFido2Request request) throws ActionException {
        try {
            logger.info("==================== disableUserFido2 start  ====================");

            ObjectMapper mapper = new ObjectMapper();
            logger.info("removeUserFido2 request: {}", request);
            String jsonBody = mapper.writeValueAsString(request);

            String apiKey = systemParamCacheManager.getValue("AIBANK", "FIDO2_HEADER_API_KEY");
            String domain = systemParamCacheManager.getValue("AIBANK", "FIDO2_API_DOMAIN_URL");
            String path = systemParamCacheManager.getValue("AIBANK", "FIDO2_API_REMOVE");

            String url = domain + path;
            logger.info("removeUserFido2 apiKey: {}, domain: {}, path: {}, url: {}", apiKey, domain, path, url);
            List<Header> header = Arrays.asList(new BasicHeader(HttpHeaders.CONTENT_TYPE, HTTP_CONTENTTYPE_JSON_UTF8),
                    new BasicHeader("ApiKey", apiKey));

            CustomHttpResponse response = HttpClient5.doPost(url, header, jsonBody);
            logger.info("removeUserFido2 response: {}", response.getResponse());

            RemoveUserFido2Response removeUserFido2Response = mapper.readValue(response.getResponse(), RemoveUserFido2Response.class);

            logger.info("==================== disableUserFido2 end  ====================");
            return removeUserFido2Response;
        }catch (Exception e){
            logger.error("FIDO2 API 使用者註銷 錯誤", e.getMessage());
            throw new ActionException(CommonErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    // FIDO2 推播狀態查詢
    public Fido2PushNotifyResponse getPushSubscriptionStatus(Fido2PushNotifyRequest request) throws ActionException {
        try {
            IdentityData identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());

            Integer companyKey = identityData.getCompanyKey();
            Integer userKey = identityData.getUserKey();
            String pushCode = request.getPushCode();
            List<MbDeviceInfoEntity> mbDeviceInfoEntities = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(companyKey, userKey, ValidateParamUtils.validParam(request.getDeviceId()));
            if (CollectionUtils.isEmpty(mbDeviceInfoEntities)) {
                logger.info("查詢 DB.AIBANK_MB_DEVICE_INFO.COMPANY_KEY = {}, 查無資料。", companyKey);
                return null;
            }

            MbDeviceInfoEntity mbDeviceInfoEntity = mbDeviceInfoEntities.get(0);
            // 取 AIBANK_MB_DEVICE_INFO.DEVICE_INFO_KEY，用來查詢 PUSH_SUBSCRIPTION
            Integer deviceInfoKey = mbDeviceInfoEntity.getDeviceInfoKey();

            Fido2PushNotifyResponse status = new Fido2PushNotifyResponse();
            status.setPushCode(pushCode);
            // 讀取 MB_DEVICE_PUSH_INFO.NOTIFY_FLAG，判斷是否授權
            List<MbDevicePushInfoEntity> mbDevicePushInfoEntities = mbDevicePushInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(companyKey, userKey, ValidateParamUtils.validParam(request.getDeviceId()));

            if (CollectionUtils.isEmpty(mbDevicePushInfoEntities)) {
                logger.info("查詢 MySQL.MB_DEVICE_PUSH_INFO.COMPANY_KEY = {}, USER_KEY = {}，查無資料。", companyKey, userKey);
                return null;
            }

            MbDevicePushInfoEntity mbDevicePushInfoEntity = mbDevicePushInfoEntities.get(0);

            Integer notifyFlag = mbDevicePushInfoEntity.getNotifyFlag() == null ? 0 : mbDevicePushInfoEntity.getNotifyFlag();
            Integer notifyPass = mbDevicePushInfoEntity.getNotifyPass() == null ? 0 : mbDevicePushInfoEntity.getNotifyPass();
            status.setNotifyFlag(notifyFlag);
            status.setNotifyPass(notifyPass);
            status.setEnable(notifyFlag == 1); // 註記是否「開啟訊息通知」
            List<PushSubscriptionEntity> pushSubscriptionEntities = pushSubscriptionRepository.findByDeviceInfoKeyAndPushCode(deviceInfoKey, pushCode);
            if (CollectionUtils.isNotEmpty(pushSubscriptionEntities)) {
                status.setSubscribe(true); // 註記是否「訂閱推播」
            }
            else {
                logger.info("客戶(COMPANY_KEY={}, USER_KEY={})，於 MySQL.PUSH_SUBSCRIPTION 查無資料。推播代號(PUSH_CODE={})。", companyKey, userKey, pushCode);
                return null;
            }
            return status;
        }catch (Exception e){
            logger.error("FIDO2 推播狀態查詢 錯誤", e.getMessage());
            throw new ActionException(CommonErrorCode.UNKNOWN_EXCEPTION, e.getMessage());
        }
    }

    // 更新 AIBANK_MB_DEVICE_INFO 表 CREDIT_CARD_FIDO2_FLAG 註記
    public Boolean updateCreditCardFido2Flag(UpdateCreditCardFido2FlagRequest request) throws ActionException, CryptException {

        IdentityData identityData = identityService.query(request.getCustId(), request.getUidDup(), request.getUserId(), request.getCompanyKind());
        if (identityData.getCompanyKey() == null || identityData.getUserKey() == null) {
            logger.error("依傳入的使用者資訊，查無資料。");
            throw ExceptionUtils.getActionException(AIBankErrorCode.USER_NOT_FOUND);
        }

        // 【FORTIFY：Access Control: Database】COMPANY_KEY、USER_KEY 就是身分識別屬性欄位
        List<MbDeviceInfoEntity> entityList = mbDeviceInfoRepository.findByCompanyKeyAndUserKeyAndDeviceUuId(identityData.getCompanyKey(), identityData.getUserKey(), ValidateParamUtils.validParam(request.getDeviceIxd()));
        if (CollectionUtils.isNotEmpty(entityList) && entityList.size() == 1) {

            // 只能綁一筆資料
            MbDeviceInfoEntity entity = entityList.get(0);
            entity.setCreditCardFido2Flag(request.getCreditCardFido2Flag());
            entity.setUpdateTime(new Date());
            mbDeviceInfoRepository.save(entity);

        }
        else {
            // 沒資料或是超過一筆資料
            throw ExceptionUtils.getActionException(CommonErrorCode.DATA_NOT_FOUND);
        }

        return true;
    }
}
