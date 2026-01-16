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
package com.tfb.aibank.biz.component.cardpromote;

import java.util.ArrayList;
import java.util.List;

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
import com.tfb.aibank.biz.component.cardpromote.model.ActivityRq;
import com.tfb.aibank.biz.component.cardpromote.model.ActivityRs;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)CardPromoteService.java
 * 
 * <p>Description:信用卡優惠網</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class CardPromoteService {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private static final IBLog logger = IBLog.getLog(CardPromoteService.class);

    /**
     * 信用卡優惠網 API- activity查詢信用卡推薦活動
     *
     * @param rq
     * @return
     * @throws ActionException
     */
    public ActivityRs doActivity(ActivityRq rq) throws ActionException {
        return doPost(rq);
    }

    /**
     * 呼叫信用卡優惠網
     */
    private ActivityRs doPost(ActivityRq rq) throws ActionException {

        logger.info("STEP: 呼叫信用卡優惠網 start");
        
        ActivityRs rs = null;

        String url = getUrl() + rq.getPath();
        
        String payload = JsonUtils.getJson(rq.getJson());
        
        logger.info("STEP: 呼叫信用卡優惠網 url: {}, payload: {}", url, payload);
        List<Header> headers = getHeaders();
        Header authorizationHeader = new BasicHeader("AuthCode", getApiAuthCode());
        headers.add(authorizationHeader);

        CustomHttpResponse response = HttpClient5.doPost(url, headers, payload, getApiTimeout(), getApiTimeout());

        int statusCode = response.getCode();

        String rsPayload = StringUtils.defaultString(response.getResponse());

        logger.debug("CardPromote Service Rs: StatusCode={}, RsPayload={}", statusCode, rsPayload);

        if (statusCode == HttpStatus.SC_SUCCESS && StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, ActivityRs.class);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.CARD_PROMOTE_API_ERROR);
        }

        return rs;
    }

    /**
     * 取得共用Header
     */
    private List<Header> getHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json"));
        headers.add(new BasicHeader(HttpHeaders.ACCEPT_CHARSET, "UTF-8"));
        return headers;
    }

    private String getApiAuthCode() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CardPromoteAuthCode");
    }

    private int getApiTimeout() {
        return ConvertUtils.str2Int(systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CardPromoteTimeOut"), 5);
    }

    private String getUrl() {
        return systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "CardPromoteUrl");
    }
}
