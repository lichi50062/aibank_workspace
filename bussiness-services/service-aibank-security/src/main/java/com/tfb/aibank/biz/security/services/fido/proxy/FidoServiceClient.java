/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.proxy;

import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.hc.core5.net.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

import com.google.gson.Gson;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.security.services.fido.bean.FidoBaseRequestBean;
import com.tfb.aibank.biz.security.services.fido.bean.FidoBaseResponseBean;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.util.BizExceptionUtils;

/**
 * @author john
 *
 */
@SuppressWarnings({ "deprecation" })
@Component
public class FidoServiceClient {

    private final IBLog logger = IBLog.getLog(FidoServiceClient.class);

    @Autowired
    protected SystemParamCacheManager systemParamCacheManager;

    private String fidoUrl;
    private static final String FIDO_URL = "FIDO_URL";

    private static final String FIDO_TIMEOUT = "FIDO_TIMEOUT";
    private Integer timeout;

    public void init(SystemParamCacheManager systemParamCacheManager) {
        fidoUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, FIDO_URL);
        String tmp = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, FIDO_TIMEOUT);
        if (tmp != null) {
            try {
                timeout = Integer.parseInt(tmp);
            }
            catch (NumberFormatException ex) {
                timeout = 30;
            }
        }
    }

    public <S extends FidoBaseResponseBean> S post2(FidoBaseRequestBean rqBean, Class<S> responseType) throws ActionException {

        S rsBean = null;
        // String path = "";
        try {

            // 組裝初始的TraceLog
            // traceLog = composeInitTraceLog(rqBean);

            StringBuilder midHost = new StringBuilder(fidoUrl);
            midHost.append(rqBean.getApi());
            String uri = midHost.toString();

            URIBuilder uriBuilder = new URIBuilder(uri);

            /** Add Params **/
            if (rqBean.getParams() != null) {
                Map<String, String> params = rqBean.getParams();

                Set<String> set = params.keySet();
                Iterator<String> it = set.iterator();
                while (it.hasNext()) {
                    String key = it.next();
                    uriBuilder.addParameter(key, params.get(key));
                    logger.info("RQ-{}={}", key, params.get(key));
                }

            }
            // path = uriBuilder.build().toString();
            //
            // if (rqBean.getInputParam() != null) {
            // path += "&InputParams=" + rqBean.getInputParam();
            // }

        }
        catch (ResourceAccessException | URISyntaxException ex) {
            // 處理後端主機Connection Timeout的問題
            logger.error(ex.getMessage(), ex);
            throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
        }

        return rsBean;
    }

    public <S extends FidoBaseResponseBean> S post(FidoBaseRequestBean rqBean, Class<S> responseType) throws ActionException {

        S rsBean = null;
        try {

            StringBuilder midHost = new StringBuilder(fidoUrl);
            midHost.append(rqBean.getApi());
            String uri = midHost.toString();

            String jsonBody = "";

            /** Add Params **/
            if (rqBean.getParams() != null) {
                Gson gson = GsonHelper.newInstance();
                jsonBody = gson.toJson(rqBean.getParams());
            }

            String content = post(uri, jsonBody, rqBean.getHeaders());

            String rsJson = StringUtils.isEmpty(content) ? "" : content;

            logger.info("====================> content={}", content);

            if (StringUtils.isNotBlank(rsJson)) {
                rsBean = JsonUtils.getObject(rsJson, responseType);
            }
            else {
                logger.info("FIDO : response body is empty!");
                throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);
            }
        }
        catch (ResourceAccessException ex) {
            logger.error(ex.getMessage(), ex);
            throw BizExceptionUtils.getActionException(ex);
        }

        return rsBean;
    }

    public String post(String url, String jsonString, Map<String, String> headers) throws ActionException {

        CustomHttpResponse response = HttpClient5.doPost(url, headers, jsonString, timeout);

        int statusCode = response.getCode();
        String rsPayload = StringUtils.defaultString(response.getResponse());
        logger.info("FIDO Service: StatusCode={}, Payload={}", statusCode, rsPayload);

        return rsPayload;
    }

}