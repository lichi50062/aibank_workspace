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
package com.tfb.aibank.chl.system.ot010.task;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import com.nimbusds.jose.shaded.gson.JsonSyntaxException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot010.model.NSTOT010010Rq;
import com.tfb.aibank.chl.system.ot010.model.NSTOT010010Rs;
import com.tfb.aibank.chl.system.ot010.service.NSTOT010Service;
import com.tfb.aibank.chl.system.resource.UserResource;
import com.tfb.aibank.chl.system.resource.dto.GenerateTokenRequest;
import com.tfb.aibank.chl.system.resource.dto.GenerateTokenResponse;
import com.tfb.aibank.chl.system.resource.dto.GetSsoSettingRequest;
import com.tfb.aibank.chl.system.resource.dto.GetSsoSettingResponse;
import com.tfb.aibank.chl.system.resource.dto.LoginData;
import com.tfb.aibank.common.type.UrlOpenType;

//@formatter:off
/**
* @(#)NSTOT010010Task.java
* 
* <p>Description:SSO 登入驗證機制 - 產生 Token</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT010010Task extends AbstractAIBankBaseTask<NSTOT010010Rq, NSTOT010010Rs> {

    private static final IBLog logger = IBLog.getLog(NSTOT010010Task.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private UserResource userResource;

    @Autowired
    protected NSTOT010Service service;

    @Override
    public void validate(NSTOT010010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT010010Rq rqData, NSTOT010010Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        String[] param = null;
        boolean isCustomeParamTYpe = false;
        String tmpUrl = rqData.getChannelKey();

        if (StringUtils.isNotBlank(rqData.getChannelKey())) {
            if (rqData.getChannelKey().indexOf("#") > -1) {
                param = rqData.getChannelKey().split("#");
                rqData.setChannelKey(param[0]);
            }

            if (rqData.getChannelKey().indexOf("&") > -1) {
                param = rqData.getChannelKey().split("&");
                rqData.setChannelKey(param[0]);
                if (StringUtils.startsWith(param[1], "customParams")) {
                    isCustomeParamTYpe = true;
                }
            }
        }

        rsData.setWhiteListWhenOpenUrl(service.getWhitList());
        if (aibankUser == null) {
            GetSsoSettingRequest request = new GetSsoSettingRequest();
            request.setChannelKey(rqData.getChannelKey());
            GetSsoSettingResponse response = userResource.getSsoSetting(request);
            if ("0".equals(response.getStatus())) {
                rsData.setAuthUrl(tmpUrl);
            }
            else {
                rsData.setAuthUrl(response.getOpenUrl());
            }
            rsData.setOpenType(response.getOpenType());
            rsData.setModuleType(response.getModuleType());
            rsData.setModuleParam(response.getModuleParam());
            rsData.setTitle(getTitle(response.getModuleParam()));
            if (StringUtils.equals("I", rsData.getOpenType())) {
                rsData.getWhiteListWhenOpenUrl().add(service.getDomain(rsData.getAuthUrl()));
            }
            else {
                getWhitListUrl(rsData.getAuthUrl(), rsData);
            }
            return;
        }
        
        GenerateTokenRequest request = new GenerateTokenRequest();
        request.setChannelKey(rqData.getChannelKey());
        request.setCustId(aibankUser.getCustId());
        request.setUserId(aibankUser.getUserId());
        request.setUidDup(aibankUser.getUidDup());
        request.setCompanyKind(aibankUser.getCompanyKind());
        request.setNameCode(aibankUser.getNameCode());
        request.setLoginData(new LoginData(aibankUser.getLoginMsgRs()));
        
        GenerateTokenResponse response = userResource.generateToken(request);

        switch (response.getStatus()) {
        case "0":
            getWhitListUrl(tmpUrl, rsData);
            break;

        case "1":
            rsData.setAuthUrl(response.getOpenUrl());
            rsData.setOpenType(response.getOpenType());
            rsData.setModuleType(response.getModuleType());
            break;

        case "2":
            rsData.setAuthUrl(getSSOUrl(response, param, isCustomeParamTYpe));
            rsData.setOpenType(response.getOpenType());
            rsData.setModuleType(response.getModuleType());

            break;
        default:
            break;
        }

        if (StringUtils.equals("I", rsData.getOpenType())) {
            rsData.setModuleParam(response.getModuleParam());
            rsData.setTitle(getTitle(response.getModuleParam()));
            rsData.getWhiteListWhenOpenUrl().add(service.getDomain(rsData.getAuthUrl()));
        }
    }

    private String getTitle(String params) {
        if (StringUtils.isBlank(params))
            return "";

        String[] pairs = params.split(",");

        for (String pair : pairs) {
            String[] p = pair.split("=");
            if (StringUtils.equals("title", p[0])) {
                return p[1];
            }
        }
        return "";
    }

    private String getSSOUrl(GenerateTokenResponse response, String[] param, boolean isCustomeParamTYpe) {
        StringBuilder authUrl = new StringBuilder();

        if (StringUtils.equals("Y", response.getSsoFlag())) {
            authUrl.append(systemParamCacheManager.getValue("AIBANK", "AIBANK_SSO_URL", "https://ebankcld.taipeifubon.com.tw/B2CCLD/GW/AuthService/sso/authenticate/req"));
        }
        else {
            authUrl.append(response.getOpenUrl());
        }
        authUrl.append("?");
        append(authUrl, "token", URLEncoder.encode(response.getToken(), StandardCharsets.UTF_8));
        append(authUrl, "sourceFrom", response.getSourceFrom());
        append(authUrl, "channelId", response.getChannelId());
        append(authUrl, "func", response.getFunc());
        append(authUrl, "txId", response.getTxId());
        if (isCustomeParamTYpe) {
            append(authUrl, "customParams", getNewCustomeParam(response.getCustomParam(), param), false);
        }
        else {
            append(authUrl, "customParams", getCustomeParam(response.getCustomParam(), param), false);
        }

        if (StringUtils.equals("O", response.getOpenType())) {
            authUrl.append("?external=1");
        }
        return authUrl.toString();
    }

    private void getWhitListUrl(String url, NSTOT010010Rs rsData) {

        rsData.setAuthUrl(url);

        rsData.setModuleType("1");

        /** 沒有白名單 */
        if (rsData.getWhiteListWhenOpenUrl() == null) {
            return;
        }

        if (StringUtils.isBlank(rsData.getOpenType())) {
            rsData.setOpenType(UrlOpenType.OPEN_EXTERNAL.getType());
            for (String whiteDomain : rsData.getWhiteListWhenOpenUrl()) {
                if (isMatchWhiteList(url, whiteDomain.trim())) {
                    rsData.setAuthUrl(url);
                    rsData.setOpenType(UrlOpenType.OPEN_IN_APP_BROWSER.getType());
                    rsData.setModuleType("1");

                }
            }
        }
        return;
    }

    private boolean isMatchWhiteList(String url, String whiteDomain) {
        if (url == null)
            return false;

        if (whiteDomain == null)
            return false;

        whiteDomain = whiteDomain.replace("*", "");

        if (whiteDomain.indexOf("/") > -1) {
            return url.contains(whiteDomain);
        }
        else {

            URI uri;
            try {
                uri = new URI(url);
            }
            catch (URISyntaxException e) {
                return false;
            }
            return (isRevertMatch(uri.getHost(), whiteDomain));
        }
    }

    private boolean isRevertMatch(String url, String whiteDomain) {
        int lenUrl = url.length();
        int lenDomain = whiteDomain.length();

        if (lenUrl < lenDomain)
            return false;

        for (int i = 0; i < lenDomain; i++) {
            char a = url.charAt(lenUrl - i - 1);
            char b = whiteDomain.charAt(lenDomain - i - 1);
            if (a != b)
                return false;
        }
        return true;
    }

    private String getNewCustomeParam(String customParam, String[] param) {

        JsonObject jsonObject = null;

        if (StringUtils.isBlank(customParam)) {
            if (param == null) {
                // 兩參數都為空
                return "";
            }
            jsonObject = new JsonObject();
        }
        else {
            jsonObject = JsonParser.parseString(customParam).getAsJsonObject();
        }

        if (param != null && param.length > 1) {

            String[] extParams = param[1].split("=");

            if (extParams.length == 2) {

                String extParam = "";
                try {
                    // fortify: Code Correctness: Byte Array to String Conversion
                    extParam = new String(Base64.getDecoder().decode(extParams[1]), StandardCharsets.UTF_8);
                }
                catch (Exception ex) {
                    logger.warn("帶入的customParam 無法解析:{}", param[1]);
                }

                if (StringUtils.isNotBlank(extParam)) {
                    try {
                        JsonObject extJson = JsonParser.parseString(extParam).getAsJsonObject();
                        if (extJson != null) {
                            for (String key : extJson.keySet()) {
                                jsonObject.add(key, extJson.get(key));
                            }
                        }
                    }
                    catch (JsonSyntaxException ex) {
                        logger.warn("帶入的customParam 無法解析:{}", param[1]);
                    }
                }
            }
        }

        try {
            return URLEncoder.encode(jsonObject.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            logger.warn("平台保留參數包含不支援Encode的字元");
        }
        return customParam;
    }

    private String getCustomeParam(String customParam, String[] param) {

        JsonObject jsonObject = null;

        if (StringUtils.isBlank(customParam)) {
            if (param == null) {
                return "";
            }
            jsonObject = new JsonObject();
        }
        else {
            jsonObject = JsonParser.parseString(customParam).getAsJsonObject();
        }

        if (param != null && param.length > 1) {

            for (String p : param) {
                if (StringUtils.isNotBlank(p)) {
                    String pp[] = p.split("=");
                    if (pp.length == 2) {
                        jsonObject.addProperty(pp[0], pp[1]);
                    }
                }

            }
        }

        try

        {
            return URLEncoder.encode(jsonObject.toString(), "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            logger.warn("平台保留參數包含不支援Encode的字元");
        }
        return customParam;
    }

    private void append(StringBuilder authUrl, String key, String val) {
        append(authUrl, key, val, true);
    }

    private void append(StringBuilder authUrl, String key, String val, boolean isAddAnd) {
        authUrl.append(key);
        authUrl.append("=");
        authUrl.append(val);
        if (isAddAnd)
            authUrl.append("&");
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
