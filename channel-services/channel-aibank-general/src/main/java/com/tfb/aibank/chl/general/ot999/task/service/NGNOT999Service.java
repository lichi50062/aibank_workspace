package com.tfb.aibank.chl.general.ot999.task.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999Rs;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.EncodeWithSecretRequest;
import com.tfb.aibank.chl.general.resource.dto.EncodeWithSecretResponse;
import com.tfb.aibank.chl.general.service.NGNService;
import com.tfb.aibank.common.type.E2EEHsmType;
import com.tfb.aibank.common.type.SsoStatusType;

//@formatter:off
/**
* @(#)NGNOT999Service.java 
* 
* <p>Description:快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Service
@Qualifier("NGNOT999Service")
public class NGNOT999Service extends NGNService {

    public static final String FAST_VALIDATE_CACHE_KEY = "FAST_VALIDATE_CACHE_KEY";

    private static final List<String> ALLOWED_HOSTS = List.of("ebankcldtest.taipeifubon.com.tw", "ebankcld.taipeifubon.com.tw");

    @Autowired
    private SecurityResource securityResource;

    public String encodewithSecret(String uid, String uuid, String secret) {
        EncodeWithSecretRequest rq = new EncodeWithSecretRequest();
        rq.setE2eeHsmType(E2EEHsmType.PWD_EB5556981_CP1047.name());
        rq.setUid(uid);
        rq.setUuid(uuid);
        rq.setEncodedSecret(secret);

        EncodeWithSecretResponse rs = securityResource.encodewithSecret(rq);
        return rs.getHostSecret();

    }

    /**
     * 組合callback URL
     * 
     * @param rsData
     * @param statusCode
     * @param token
     * @param platformParams
     */
    public void generateCallBackUrl(NGNOT999Rs rsData, String statusCode, String token, String platformParams, String openType, String moduleType, String moduleParam) {
        StringBuilder sb = new StringBuilder();

        rsData.setStatusCode(statusCode);

        sb.append(systemParamCacheManager.getValue("AIBANK", "LOGIN_CALLBACK_URL"));
        sb.append("?");
        sb.append("statusCode=");
        sb.append(statusCode);
        sb.append("&");

        sb.append("statusDesc=");
        sb.append(SsoStatusType.find(statusCode).getMemo());

        if (StringUtils.isNotBlank(token)) {
            sb.append("&");
            sb.append("token=");
            sb.append(token);
        }

        if (StringUtils.isNotBlank(platformParams)) {
            sb.append("&");
            sb.append("platformParams=");
            sb.append(platformParams);
        }

        rsData.setCallBackUrl(sb.toString());

        if (StringUtils.equals(openType, "I")) {
            rsData.setWhiteListWhenOpenUrl(getWhitList());
            rsData.getWhiteListWhenOpenUrl().add(getDomain(rsData.getCallBackUrl()));
        }
        rsData.setOpenType(openType);
        rsData.setModuleType(moduleType);
    }
     


    public List<String> getWhitList() {
        String whiteListString = dicCacheManager.getValue("AIBANK", "WHITE_LIST");

        /** 沒有白名單 */
        if (StringUtils.isBlank(whiteListString)) {
            logger.warn("沒有白名單");
            return null;
        }
        whiteListString = whiteListString.replace("*.", "");

        String[] whiteListArray = whiteListString.trim().split(",");

        return new ArrayList<String>(Arrays.asList(whiteListArray));
    }

    public String getDomain(String href) {
        if (href == null)
            return "";

        // fortify: resource injection
        URI uri;
        try {
            href = ValidateParamUtils.validParam(href);
            uri = new URI(sanitizeURL(href));
            if (!ALLOWED_HOSTS.contains(uri.getHost())) {
                throw new IllegalArgumentException("remote resource host not allowed");
            }
        }
        catch (URISyntaxException e) {
            logger.error("error parsing remote URL", e);
            throw new IllegalArgumentException("invalid remote url");
        }
        return uri.getHost();
    }

    /**
     * 過濾 URL 中的特殊字符，以防止 XSS 攻擊
     */
    public String sanitizeURL(String url) {
        return url.replaceAll("[<>\"']", "");
    }
}
