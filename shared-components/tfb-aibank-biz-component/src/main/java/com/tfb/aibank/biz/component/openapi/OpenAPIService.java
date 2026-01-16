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
package com.tfb.aibank.biz.component.openapi;

import java.util.ArrayList;
import java.util.List;

import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHeaders;
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
import com.tfb.aibank.biz.component.openapi.model.OpenAPIBankListRq;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIBankListRs;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIPreTransferInfoQryRq;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIPreTransferInfoQryRs;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIRq;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIRs;
import com.tfb.aibank.biz.component.openapi.model.OpenAPISystemParam;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIUserBindRq;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIUserBindRs;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIUserChangeAccountRq;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIUserQueryRq;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIUserQueryRs;
import com.tfb.aibank.biz.component.openapi.model.OpenAPIUserUnbindRq;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)OpenAPIService.java
 * 
 * <p>Description:OpenAPI介接服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class OpenAPIService {

    private static final IBLog logger = IBLog.getLog(OpenAPIService.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    /**
     * 註冊客戶綁定
     * 
     * @param rq
     * @return
     * @throws ActionException
     */
    public String doUserBind(OpenAPIUserBindRq rq) throws ActionException {
        return doPost(rq, OpenAPIUserBindRs.class).getCode();
    }

    /**
     * 查詢客戶綁定
     * 
     * @param rq
     * @return
     * @throws ActionException
     */
    public OpenAPIUserQueryRs doUserQuery(OpenAPIUserQueryRq rq) throws ActionException {

        return doPost(rq, OpenAPIUserQueryRs.class);
    }

    /**
     * 取消客戶綁定
     * 
     * @param rq
     * @return
     * @throws ActionException
     */
    public String doUserUnbind(OpenAPIUserUnbindRq rq) throws ActionException {
        return doPost(rq, OpenAPIUserBindRs.class).getCode();
    }

    /**
     * 更新客戶綁定
     * 
     * @param rq
     * @return
     * @throws ActionException
     */
    public String doUserAccountChange(OpenAPIUserChangeAccountRq rq) throws ActionException {
        return doPost(rq, OpenAPIUserBindRs.class).getCode();
    }

    /**
     * 轉帳交易前代理行查詢收款戶資訊
     * 
     * @param rq
     * @return
     * @throws ActionException
     */
    public OpenAPIPreTransferInfoQryRs preTransferInfoQry(OpenAPIPreTransferInfoQryRq rq) throws ActionException {

        return doPost(rq, OpenAPIPreTransferInfoQryRs.class);
    }

    /**
     * 查詢參加單位
     * 
     * @return
     * @throws ActionException
     */
    public OpenAPIBankListRs getBankList() throws ActionException {

        return doPost(new OpenAPIBankListRq(), OpenAPIBankListRs.class);
    }

    /**
     * 執行API Post請求
     * 
     * @param rq
     * @param responseType
     * @return
     * @throws ActionException
     */
    private <S extends OpenAPIRs> S doPost(OpenAPIRq rq, Class<S> responseType) throws ActionException {

        S rs = null;

        StringBuilder sb = new StringBuilder(systemParamCacheManager.getValue(OpenAPISystemParam.OPEN_API_SERVER_URL));
        sb.append(rq.getPath());

        String url = sb.toString();
        String payload = JsonUtils.getJson(rq);
        int timeout = ConvertUtils.str2Int(systemParamCacheManager.getValue(OpenAPISystemParam.OPEN_API_CONNECTION_TIMEOUT), 60);
        // Fortify:Log Forging //logger.info("OpenAPI Service: Call={}, Payload={}, Timeout={}", url, payload, timeout);

        List<Header> headers = getHeaders();
        CustomHttpResponse response = HttpClient5.doPost(url, headers, payload, timeout);

        int statusCode = response.getCode();
        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.info("OpenAPI Service: StatusCode={}, Payload={}", statusCode, rsPayload);

        if (StringUtils.isNotBlank(rsPayload)) {
            rs = JsonUtils.getObject(rsPayload, responseType);
        }
        else {
            throw ExceptionUtils.getActionException(AIBankErrorCode.OPEN_API_SERVICE_ERROR);
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
        headers.add(new BasicHeader(HttpHeaders.AUTHORIZATION, "Bearer " + systemParamCacheManager.getValue(OpenAPISystemParam.OPEN_API_AUTH_TOKEN)));
        return headers;
    }

}
