package com.tfb.aibank.biz.user.services.linebindcheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import com.tfb.aibank.biz.user.services.linebindcheck.model.LineBindCheckModel;
import com.tfb.aibank.biz.user.services.linebindcheck.model.LineBindCheckRequest;
import com.tfb.aibank.biz.user.services.linebindcheck.model.LineBindStatusResponse;
import com.tfb.aibank.common.code.AIBankConstants;

/**
 // @formatter:off
 * @(#)LineBindCheckService.java
 * 
 * <p>Description:[發查Line Bind綁定狀態 API]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LineBindCheckService {

    private static final IBLog logger = IBLog.getLog(LineBindCheckService.class);
    // 以下為連線參數
    // 測試環境：http://efincloudtest.cldatu.groupt.fbt.com/EFIN/lineBBCService/lineAiBank/bindedCheck
    // 正式環境：http://efincloudtest.cldatu.groupt.fbt.com/EFIN/lineBBCService/lineAiBank/bindedCheck
    private static final String LINEPNP_URL_WHOLE = "LINEPNP_URL_WHOLE";
    // API路徑
    private static final String BIND_CHECK_WHOLE_URI = 
    		"http://efincloudtest.cldatu.groupt.fbt.com/EFIN/lineBBCService/lineAiBank/bindedCheck"; // LINEBC綁定檢核
    // HTTP 連線參數
    private static final String HTTP_METHOD_POST = "POST";
    private static final String HTTP_HEADER_CONTENTTYPE = "Content-Type";
    private static final String HTTP_CONTENTTYPE_JSON = "application/json;charset=utf8";
    private static final long HTTP_CONNECT_TIMEOUT = 10000;
    private static final int HTTP_CONNECT_TIMEOUT_INT = 10000;
    private static final long HTTP_READ_TIMEOUT = 15000;
    private static final int HTTP_READ_TIMEOUT_INT = 15000;
    private static final int MAX_LINE_LENGTH = 1000000;
    private SystemParamCacheManager systemParamCacheManager;

    public LineBindCheckService(SystemParamCacheManager systemParamCacheManager) {
        this.systemParamCacheManager = systemParamCacheManager;
    }

    public String checkLineBindStatus(String custId) {
        String bindStatus = "N";
        try {
            String httpURL = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, LINEPNP_URL_WHOLE);
            
            if (StringUtils.isBlank(httpURL)) {
            	httpURL = BIND_CHECK_WHOLE_URI;
                logger.warn("未設定 LINEPNP_URL_WHOLE");
            }

            RestTemplate rest = new RestTemplateBuilder().setConnectTimeout(Duration.ofMillis(HTTP_CONNECT_TIMEOUT)).setReadTimeout(Duration.ofMillis(HTTP_READ_TIMEOUT)).build();

            LineBindCheckRequest req = new LineBindCheckRequest(custId, System.currentTimeMillis());
            logger.debug("httpURL: {}", httpURL);
            LineBindCheckModel result = rest.postForObject(httpURL, req, LineBindCheckModel.class);
            logger.debug("API result: {}", IBUtils.attribute2Str(result));
            if (null != result) {
                bindStatus = result.getIsBind();
            }
        }
        catch (RestClientException e) {
            logger.warn("Request for linebind sts error: {}", e.getMessage());
        }
        return bindStatus;
    }

    public LineBindStatusResponse checkLineBindStatusOldCode(String custIxd) {

        LineBindStatusResponse rs = new LineBindStatusResponse();
        String bindStatus = "N";

        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("idno", custIxd);
        rqBody.addProperty("time", System.currentTimeMillis());

        JsonObject response = null;
        try {
            response = postAPI(rqBody);
            if (null != response && "0000".equals(response.get("errorCode").getAsString())) {
                // Y:有綁定LINE好友
                // N:無綁定LINE好友
                String isBind = response.get("isBind").getAsString();
                bindStatus = isBind;
            }
            if (null != response) {
                rs.setErrorCode(response.get("errorCode").getAsString());
                rs.setErrorMessage(response.get("errorMessage").getAsString());
            }
        }
        catch (ActionException ex) {
            logger.error(String.format("checkLineBindStatusOldCode(String)...%s", ex.getMessage()), ex);
        }

        rs.setBindStatus(bindStatus);
        return rs;
    }

    private JsonObject postAPI(JsonObject bodyObj) throws ActionException {

        BufferedReader in = null;
        HttpURLConnection con = null;
        long startTime = 0; // 上行開始時間
        long period = 0; // 收送時間間隔
        try {
    		// API路徑改由系統參數檔獲取URL
            // 測試環境：http://efincloudtest.cldatu.groupt.fbt.com/EFIN/lineBBCService/lineAiBank/bindedCheck
            // 正式環境：http://efincloudtest.cldatu.groupt.fbt.com/EFIN/lineBBCService/lineAiBank/bindedCheck
    		String uriStr = systemParamCacheManager.getValue(
    				AIBankConstants.CHANNEL_NAME, LINEPNP_URL_WHOLE,BIND_CHECK_WHOLE_URI);
            // 初始化 http connection
            URL url = new URL(uriStr);
            con = (HttpURLConnection) url.openConnection();
            // 設定 http method "POST"
            con.setRequestMethod(HTTP_METHOD_POST);
            // 設定 http header, ContentType為json
            con.setRequestProperty(HTTP_HEADER_CONTENTTYPE, HTTP_CONTENTTYPE_JSON);
            // 設定連線逾時，送到Server的時間
            con.setConnectTimeout(HTTP_CONNECT_TIMEOUT_INT);
            // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
            con.setReadTimeout(HTTP_READ_TIMEOUT_INT);
            con.setDoOutput(true);

            startTime = System.currentTimeMillis();
            logger.debug("發送Request URI: " + url.getPath() + " JSON: " + bodyObj.toString());

            try (OutputStream os = con.getOutputStream()) {
                os.write(bodyObj.toString().getBytes(StandardCharsets.UTF_8));
            }

            // 確認回傳HTTP status為200
            int status = con.getResponseCode();
            period = System.currentTimeMillis() - startTime;
            if (status == 200) {
                try (InputStreamReader streamReader = new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8)) {
                    // 將stream放入buffer reader
                    in = new BufferedReader(streamReader);
                    String line;
                    StringBuffer content = new StringBuffer(0);
                    int maxLines = Integer.MAX_VALUE - 2; // 設置最大讀取行數限制
                    int lineCount = 0;
                    // 一次讀取一行並加入到content後方
                    int i = 0;
                    while (true) {
                        i++;
                        if (lineCount++ >= maxLines) {
                            throw new IOException("File too large, exceeding maximum line limit.");
                        }
                        line = in.readLine();

                        if (line == null || i > 1000) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_LENGTH));
                    }
                    // 輸出結果
                    logger.info("取得Response JSON(" + period + "ms):" + content);
                    // 解析結果
                    JsonElement jelement = JsonParser.parseString(content.toString());
                    JsonObject data = jelement.getAsJsonObject();
                    return data;
                }

            }
            else {
                logger.info("失敗狀態:{}", status);
                try (InputStreamReader streamReader = new InputStreamReader(con.getErrorStream(), StandardCharsets.UTF_8)) {
                    int maxLines = Integer.MAX_VALUE - 2; // 設置最大讀取行數限制
                    int lineCount = 0;
                    // 將stream放入buffer reader
                    in = new BufferedReader(streamReader);
                    String line;
                    StringBuffer content = new StringBuffer(0);
                    int i = 0;
                    while (true) {
                        if (lineCount++ >= maxLines) {
                            throw new IOException("File too large, exceeding maximum line limit.");
                        }
                        i++;
                        line = in.readLine();

                        if (line == null || i > 1000) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_LENGTH));
                    }
                    // 輸出結果
                    logger.info("取得Response JSON(" + period + "ms):" + content);
                    // 解析結果
                    JsonElement jelement = JsonParser.parseString(content.toString());
                    JsonObject data = jelement.getAsJsonObject();

                    return data;
                }
            }
        }
        catch (Exception ex) { // 【Fortify】API產生的所有例外，封裝成 ActionException
            logger.error("LINEPNP請求錯誤: " + ex);
            throw ExceptionUtils.getActionException(ex);
        }
        finally {
            // Close BufferedReader
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                logger.error("== postAPI has IOException ==");
                logger.error(e.getMessage(), e);
            }
            // Close HttpURLConnection
            if (con != null) {
                con.disconnect();
            }
        }
    }
}