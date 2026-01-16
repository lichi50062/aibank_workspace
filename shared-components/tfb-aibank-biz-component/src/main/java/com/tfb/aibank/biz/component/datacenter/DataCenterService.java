/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.datacenter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.datacenter.model.DataCenterBaseRq;
import com.tfb.aibank.biz.component.datacenter.model.DataCenterBaseRs;
import com.tfb.aibank.biz.component.datacenter.model.OauthRq;
import com.tfb.aibank.biz.component.datacenter.model.OauthRs;
import com.tfb.aibank.biz.component.datacenter.model.OfferRankingRq;
import com.tfb.aibank.biz.component.datacenter.model.OfferRankingRs;
import com.tfb.aibank.biz.component.datacenter.model.PromoStatsRq;
import com.tfb.aibank.biz.component.datacenter.model.PromoStatsRs;
import com.tfb.aibank.biz.component.datacenter.model.TopticRequest;
import com.tfb.aibank.biz.component.datacenter.model.TopticResponse;
import com.tfb.aibank.biz.component.datacenter.model.UserTagRq;
import com.tfb.aibank.biz.component.datacenter.model.UserTagRs;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)DataCenterService.java
 * 
 * <p>Description:數據中台介接服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/08, Alex PY Li 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class DataCenterService {

    private static final IBLog logger = IBLog.getLog(DataCenterService.class);

    String oauthToken;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    /**
     * 透過數據中台獲取使用者身上的標籤或是個人資訊
     * 
     * @param rq
     * @return
     * @throws ActionException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public UserTagRs doUserTag(UserTagRq rq) throws ActionException {
        return doGet(rq, true);
    }

    /**
     * 為了重複的queryName 額外實作doGet
     * 
     * @param rq
     * @param reloadToken
     *            驗證失敗需要重新提取token
     * 
     * @return
     * @throws ActionException
     */
    private UserTagRs doGet(UserTagRq rq, boolean reloadToken) throws ActionException {
        UserTagRs rs = null;
        StringBuilder sb = new StringBuilder(getBaseurl());
        sb.append("/api/");
        sb.append(getApiVersion());
        sb.append(rq.getPath());
        String url = sb.toString();

        List<Header> headers = getHeaders();

        Header authorizationHeader = new BasicHeader("Authorization", "Bearer " + getToken());
        headers.add(authorizationHeader);

        List<NameValuePair> parameters = rqData2Parameter(rq);
        parameters.add(new BasicNameValuePair("tagId", "t_p_01"));
        parameters.add(new BasicNameValuePair("tagId", "t_f_01"));
        CustomHttpResponse response = HttpClient5.doGet(url, headers, parameters, getApiTimeout());

        int statusCode = response.getCode();

        if (statusCode == HttpStatus.SC_UNAUTHORIZED && reloadToken) {
            OauthRs oauthRs = getOauthToken();

            if (oauthRs != null && StringUtils.isNotBlank(oauthRs.getAccess_token())) {
                this.oauthToken = oauthRs.getAccess_token();
            }

            return doGet(rq, false);
        }
        else if (statusCode == HttpStatus.SC_NOT_FOUND) {
            logger.error("數據中台無資料");
        }

        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.debug("DataCenter Service: StatusCode={}, Payload={}", statusCode, rsPayload);

        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, UserTagRs.class);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }

        return rs;
    }

    /**
     * B2E送情境主題至數據中台
     *
     * @param rq
     * @return
     * @throws ActionException
     */
    public TopticResponse doToptic(TopticRequest rq) throws ActionException {

        TopticResponse rs = null;

        String url = getTopticUrl();
        String payload = JsonUtils.getJson(rq);
        logger.debug("DataCenter Service Rq: Call={}, Payload={}", url, payload);
        List<Header> headers = getHeaders();
        CustomHttpResponse response = HttpClient5.doPost(url, headers, payload, getApiTimeout());

        int statusCode = response.getCode();

        if (statusCode == HttpStatus.SC_NOT_FOUND) {
            logger.error("數據中台無資料");
        }

        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.debug("DataCenter Service Rs: StatusCode={}, Payload={}", statusCode, rsPayload);

        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, TopticResponse.class);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }

        return rs;
    }

    /**
     * 透過數據中台獲取該版位符合使用者之已排序情境清單
     * 
     * @param rq
     * @return
     * @throws ActionException
     */
    public OfferRankingRs doOfferRaking(OfferRankingRq rq) throws ActionException {
        return doPostWithToken(rq, OfferRankingRs.class, true);
    }

    /**
     * 透過數據中台獲取取得客戶所屬標籤(優惠)
     *
     * @param rq
     * @return
     * @throws ActionException
     */
    public PromoStatsRs doPromoStats(PromoStatsRq rq) throws ActionException {
        return doPromoStats(rq, true);
    }

    /**
     * 透過數據中台獲取取得客戶所屬標籤(優惠)
     *
     * @param rq
     * @return
     * @throws ActionException
     */
    public PromoStatsRs doPromoStats(PromoStatsRq rq, boolean reloadToken) throws ActionException {
        logger.info("DataCenter Service doPromoStats: token={} start", rq.getToken());
        PromoStatsRs rs = null;
        String url = getBaseurl() + "/api/" + getApiVersion() + rq.getPath();

        logger.info("DataCenter Service doPromoStats: url={} ", url);

        List<Header> headers = getHeaders();

        Header authorizationHeader = new BasicHeader("Authorization", "Bearer " + getToken());
        headers.add(authorizationHeader);

        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("cust_id", rq.getToken()));
        CustomHttpResponse response = HttpClient5.doGet(url, headers, parameters, getApiTimeout());

        logger.info("DataCenter Service doPromoStats: response => {} ", response);

        int statusCode = response.getCode();

        if (statusCode == HttpStatus.SC_UNAUTHORIZED && reloadToken) {
            OauthRs oauthRs = getOauthToken();

            if (oauthRs != null && StringUtils.isNotBlank(oauthRs.getAccess_token())) {
                this.oauthToken = oauthRs.getAccess_token();
            }

            return doPromoStats(rq, false);
        }
        else if (statusCode == HttpStatus.SC_NOT_FOUND) {
            logger.error("數據中台無資料");
        }

        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.info("DataCenter Service: StatusCode={}, Payload={}", statusCode, rsPayload);
        // logger.info("DataCenter Service doPromoStats: token={} end", rq.getToken());
        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, PromoStatsRs.class);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }

        return rs;
    }

    /**
     * 取得Oauth Token
     * 
     * @return
     * @throws ActionException
     */
    private OauthRs getOauthToken() throws ActionException {
        OauthRs rs = new OauthRs();
        OauthRq rq = new OauthRq();
        rq.setGrant_type(this.getGrantType());
        rq.setClient_id(this.getClientId());
        rq.setClient_secret(this.getClientSecret());
        return getToken(rq, rs);
    }

    /**
     * rq轉換成nameValuePair
     * 
     * @param rq
     * @return
     */
    private List<NameValuePair> rqData2Parameter(DataCenterBaseRq rq) {
        List<NameValuePair> parameters = new ArrayList<>();
        Class<?> clazz = rq.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            ReflectionUtils.makeAccessible(field); // Allow access to private fields
            String propertyName = field.getName();
            try {
                Object propertyValue = field.get(rq);
                parameters.add(new BasicNameValuePair(propertyName, propertyValue.toString()));
            }
            catch (IllegalAccessException e) {
                logger.error("request data 轉換失敗:", e);
            }
        }
        return parameters;
    }

    /**
     * 呼叫數據中台拿取token
     *
     * @param rq
     * @param rs
     * @return
     * @throws ActionException
     */
    private OauthRs getToken(OauthRq rq, OauthRs rs) throws ActionException {

        StringBuilder sb = new StringBuilder(getBaseurl());
        sb.append("/api");
        sb.append(rq.getPath());

        String url = sb.toString();

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded"));
        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", rq.getGrant_type());
        formData.put("client_id", rq.getClient_id());
        formData.put("client_secret", rq.getClient_secret());
        CustomHttpResponse response = HttpClient5.doPost(url, headers, formData, getApiTimeout());

        int statusCode = response.getCode();
        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.debug("DataCenter Service Response: StatusCode={}, Payload={}", statusCode, rsPayload);

        if (statusCode == HttpStatus.SC_NOT_FOUND) {
            logger.error("數據中台無資料");
        }

        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, OauthRs.class);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }

        return rs;
    }

    /**
     * 呼叫數據中台
     * 
     * @param <S>
     * @param rq
     * @param responseType
     * @param reloadToken
     *            驗證失敗需要重新提取token
     * @return
     * @throws ActionException
     */
    private <S extends DataCenterBaseRs> S doPostWithToken(DataCenterBaseRq rq, Class<S> responseType, boolean reloadToken) throws ActionException {

        S rs = null;

        StringBuilder sb = new StringBuilder(getBaseurl());
        sb.append("/api/");
        sb.append(getApiVersion());
        sb.append(rq.getPath());

        String url = sb.toString();
        String payload = JsonUtils.getJson(rq);
        logger.debug("DataCenter Service Rq: Call={}, Payload={}", url, payload);
        List<Header> headers = getHeaders();
        Header authorizationHeader = new BasicHeader("Authorization", "Bearer " + getToken());
        headers.add(authorizationHeader);

        CustomHttpResponse response = HttpClient5.doPost(url, headers, payload, getApiTimeout());

        int statusCode = response.getCode();

        if (statusCode == HttpStatus.SC_UNAUTHORIZED && reloadToken) {
            OauthRs oauthRs = getOauthToken();

            if (oauthRs != null && StringUtils.isNotBlank(oauthRs.getAccess_token())) {
                this.oauthToken = oauthRs.getAccess_token();
            }

            return doPostWithToken(rq, responseType, false);
        }
        else if (statusCode == HttpStatus.SC_NOT_FOUND) {
            logger.error("數據中台無資料");
        }

        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.debug("DataCenter Service Rs: StatusCode={}, Payload={}", statusCode, rsPayload);

        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, responseType);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }

        return rs;
    }

    /**
     * 取得共用Header
     * 
     * @return
     */
    private List<Header> getHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "UTF-8"));
        return headers;
    }

    private String getToken() throws ActionException {
        if (StringUtils.isBlank(this.oauthToken)) {
            OauthRs oauthRs = getOauthToken();
            if (oauthRs != null && StringUtils.isNotBlank(oauthRs.getAccess_token())) {
                this.oauthToken = oauthRs.getAccess_token();
            }
        }
        return this.oauthToken;
    }

    private String getGrantType() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_GRANT_TYPE", "client_credentials");
    }

    private String getClientId() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_CLIENT_ID", "ai_bank");
    }

    private String getClientSecret() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_CLIENT_SECRET", "ai_bank_secret");
    }

    private String getBaseurl() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_API_BASE_URL");
    }

    private String getApiVersion() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_API_VERSION");
    }

    private int getApiTimeout() {
        return ConvertUtils.str2Int(systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_API_TIMEOUT"), 60);
    }

    private String getTopticUrl() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DATA_PLATFORM_TOPTIC_URL");
    }
}
