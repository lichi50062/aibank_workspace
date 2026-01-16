package com.tfb.aibank.biz.component.applyShortener;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.httpclient.CustomHttpResponse;
import com.ibm.tw.commons.httpclient.HttpClient5;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.applyShortener.model.RecommendInfoRes;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

// @formatter:off
/**
 * @(#)ApplyShortenerService.java
 *
 * <p>Description:短網址介接服務</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/16, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class ApplyShortenerService {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private static final IBLog logger = IBLog.getLog(ApplyShortenerService.class);

    public RecommendInfoRes getShortenerParams(String urlCode) throws ActionException {

        String apiUrlBase = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME,"APPLY_SHORTENER_URL");
        if (StringUtils.isBlank(apiUrlBase)) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.APPLY_SHORTENER_URL_NOT_SET);
        }

        String apiURL = UriComponentsBuilder
                .fromHttpUrl(apiUrlBase)
                .queryParam("urlCode", urlCode)  // 會自動 UTF-8 encode
                .encode()
                .toUriString();

        Map<String, String> headers = Map.of();

        try {
            String payload;
            try (CustomHttpResponse resp = HttpClient5.doGet(apiURL, headers, null)) {
                payload = resp.getResponse();
            }

            if (JsonUtils.isJson(payload)) {
                return JsonUtils.getObject(payload, RecommendInfoRes.class);
            } else {
                logger.warn("短網址服務回非 JSON：{}", payload);
                return null;
            }

        }catch (Exception ex) {                    // 其他不可預期錯誤
            logger.error("短網址 API 意外例外", ex);
            throw new RuntimeException(ex);
        }
    }
}
