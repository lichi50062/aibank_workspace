package com.tfb.aibank.biz.component.videoauthapi;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicHeader;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.tfb.aibank.biz.component.videoauthapi.model.CheckServiceStatusResponse;
import com.tfb.aibank.biz.component.videoauthapi.model.CheckWaitCountResponse;

// @formatter:off
/**
 * @(#)VideoAuthAPI.java
 * 
 * <p>Description:[視訊驗證API共用元件]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class VideoAuthAPI {

    private static final IBLog logger = IBLog.getLog(VideoAuthAPI.class);

    private static final String CONTENT_TYPE_URLENCODED_UTF8 = "application/x-www-form-urlencoded; charset=utf-8";
    private static final int CONNECTION_TIMEOUT = 30000;
    private static final int SOCKET_TIMEOUT = 60000;

    @Autowired
    private VideoAuthProperties prop;

    /**
     * 向視訊系統查詢客服是否在線服務
     * 
     * @return
     * @throws ActionException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public CheckServiceStatusResponse checkServiceStatus(String serviceNo) throws ActionException {

        CheckServiceStatusResponse resp = new CheckServiceStatusResponse();

        // 請求body
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("tenantID", "3"));
        formParams.add(new BasicNameValuePair("entityTypeID", "15"));
        formParams.add(new BasicNameValuePair("siteID", serviceNo));
        logger.info("checkServiceStatus requestBody :{}", JsonUtils.getJson(formParams));

        List<Header> headers = List.of(new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_URLENCODED_UTF8));

        CustomHttpResponse httpResponse = HttpClient5.doPost(getCheckServiceStatusPath(), headers, formParams, CONNECTION_TIMEOUT, SOCKET_TIMEOUT);

        logger.info("checkServiceStatus:{}", httpResponse.getResponse());
        resp = JsonUtils.getObject(httpResponse.getResponse(), CheckServiceStatusResponse.class);
        return resp;
    }

    /**
     * 向視訊系統查詢目前等待人數
     * 
     * @return
     * @throws ActionException
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public CheckWaitCountResponse checkWaitCount(String channel) throws ActionException {

        CheckWaitCountResponse resp = new CheckWaitCountResponse();

        // 請求body
        List<NameValuePair> formParams = new ArrayList<>();
        formParams.add(new BasicNameValuePair("VQ", prop.getVideoAuthApiCheckWaitCountVQ()));
        formParams.add(new BasicNameValuePair("Skill", prop.getVideoAuthApiCheckWaitCountSkill()));
        formParams.add(new BasicNameValuePair("Channel", channel));
        logger.info("CheckWaitCount requestBody:{}", JsonUtils.getJson(formParams));

        List<Header> headers = List.of(new BasicHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_URLENCODED_UTF8));
        CustomHttpResponse httpResponse = HttpClient5.doPost(getCheckWaitCountPath(), headers, formParams, CONNECTION_TIMEOUT, SOCKET_TIMEOUT);

        logger.info("CheckWaitCount:{}", httpResponse.getResponse());
        resp = JsonUtils.getObject(httpResponse.getResponse(), CheckWaitCountResponse.class);

        return resp;
    }

    /**
     * 取得客服是否在線API路徑
     * 
     * @return
     */
    private String getCheckServiceStatusPath() {
        return prop.getVideoAuthApiCheckServiceStatusPath();
    }

    /**
     * @return the videoAuthApiPath
     */
    public String getVideoAuthApiPath() {
        return prop.getVideoAuthApiPath();
    }

    /**
     * 取得等待人數API路徑
     * 
     * @return
     */
    private String getCheckWaitCountPath() {
        return prop.getVideoAuthApiCheckWaitCountPath();
    }
}
