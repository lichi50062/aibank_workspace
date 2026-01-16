package com.tfb.aibank.biz.component.oauth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.datacenter.model.OauthRs;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;

@Service
public class OAuthApiTokenService {

    private static final IBLog logger = IBLog.getLog(OAuthApiTokenService.class);

    private String oauthToken;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private static final String OAUTH_API_HOST = "OAUTH_API_HOST";

    private static final String ACCESS_TOKEN_URL = "OAUTH_ACCESS_TOKEN_URL";

    private static final String GRANT_TYPE = "OAUTH_API_GRANT_TYPE";

    private static final String CLIENT_ID = "OAUTH_API_CLIENT_ID";

    private static final String CLIENT_SECRXT = "OAUTH_API_CLIENT_SECRET";

    private static final String API_TIMEOUT = "OAUTH_API_TIMEOUT";

    public String getToken() throws ActionException {
        if (StringUtils.isBlank(this.oauthToken))
            this.oauthToken = fetchNewToken();
        return this.oauthToken;
    }

    public void clearToken() {
        this.oauthToken = null;
    }

    private String fetchNewToken() throws ActionException {
        String url = String.format("%s%s", getOAuthHost(), getAccessTokenUrl());

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded"));
        Map<String, String> formData = new HashMap<>();
        formData.put("grant_type", getGrantType());
        formData.put("client_id", getClientId());
        formData.put("client_secret", getClientSecrxt());
        logger.debug("OAuth api service getToken: url -> {}, formData -> {}", url, formData);
        CustomHttpResponse response = HttpClient5.doPost(url, headers, formData, getApiTimeout());

        int statusCode = response.getCode();
        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.debug("OAuth API service getToken response: statusCode -> {}, payload -> {}", statusCode, rsPayload);

        if (statusCode == HttpStatus.SC_NOT_FOUND) {
            logger.error("OAuth API No Data");
        }

        if (StringUtils.isNotBlank(rsPayload)) {
            return JsonUtils.getObject(rsPayload, OauthRs.class).getAccess_token();
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.DATA_CENTER_API_ERROR);
        }

    }

    private String getOAuthHost() {
        String host = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, OAUTH_API_HOST);
        if (StringUtils.isNotBlank(host) && host.charAt(host.length() - 1) == '/')
            host = host.substring(0, host.length() - 1);
        return host;
    }

    private String getAccessTokenUrl() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, ACCESS_TOKEN_URL);
    }

    private String getGrantType() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, GRANT_TYPE, "client_credentials");
    }

    private String getClientId() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, CLIENT_ID, "ai_bank");
    }

    private String getClientSecrxt() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, CLIENT_SECRXT, "ai_bank_secret");
    }

    private int getApiTimeout() {
        return ConvertUtils.str2Int(systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, API_TIMEOUT), 60);
    }
}
