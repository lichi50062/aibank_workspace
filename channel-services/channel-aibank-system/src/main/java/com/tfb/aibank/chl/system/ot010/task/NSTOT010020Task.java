package com.tfb.aibank.chl.system.ot010.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.chl.system.ot010.model.NSTOT010020Rq;
import com.tfb.aibank.chl.system.ot010.model.NSTOT010020Rs;
import com.tfb.aibank.chl.system.ot010.service.NSTOT010Service;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
* @(#)NSTOT010020Task.java
* 
* <p>Description:數位客服</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NSTOT010020Task extends AbstractAIBankBaseTask<NSTOT010020Rq, NSTOT010020Rs> {

    private AESCipherUtils aesCipherUtils = null;

    private static String FROM_DEICE = "fromDevice=aibank";

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private OtpAuthService otpAuthService;

    @Autowired
    protected NSTOT010Service service;

    @Override
    public void validate(NSTOT010020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT010020Rq rqData, NSTOT010020Rs rsData) throws ActionException {

        StringBuilder digitalChatUrl = new StringBuilder(1024);
        String url = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DIGITAL_CHAT_URL");
        digitalChatUrl.append(url);
        digitalChatUrl.append("?");

        // 已登入
        if (isLoggedIn()) {
            if (StringUtils.isNotBlank(rqData.getErrorCode())) {
                digitalChatUrl.append("q=");
                digitalChatUrl.append(rqData.getErrorCode());
                digitalChatUrl.append("&");
            }
            if (StringUtils.isNotBlank(rqData.getFromDevice())) {
                digitalChatUrl.append("fromDevice=" + rqData.getFromDevice());
            }
            else {
                digitalChatUrl.append(FROM_DEICE);
            }
            digitalChatUrl.append("&");

            AIBankUser loginUser = getLoginUser();
            String authString = getAuthString(loginUser);
            digitalChatUrl.append("auth=");
            digitalChatUrl.append(authString);

        }

        else {
            if (StringUtils.isNotBlank(rqData.getErrorCode())) {
                digitalChatUrl.append("q=");
                digitalChatUrl.append(rqData.getErrorCode());
                digitalChatUrl.append("&");
            }
            if (StringUtils.isNotBlank(rqData.getFromDevice())) {
                digitalChatUrl.append("fromDevice=" + rqData.getFromDevice());
            }
            else {
                digitalChatUrl.append(FROM_DEICE);
            }
        }

        rsData.setDigitalChatUrl(digitalChatUrl.toString());
        rsData.setWhiteListWhenOpenUrl(service.getWhitList());
        rsData.getWhiteListWhenOpenUrl().add(service.getDomain(rsData.getDigitalChatUrl()));
    }

    /**
     * 取得客服AES 加密
     * 
     * @param loginUser
     * @return
     * @throws ActionException
     * @throws Exception
     */
    public String getAuthString(AIBankUser loginUser) throws ActionException {
        String result = "";
        String code = "";
        // 判斷是否信用卡/一般 會員
        if (CustomerType.isGeneral(loginUser.getCustomerType().getLoginType())) {
            // 一般
            code = "B01";
        }
        else {
            // 信用卡
            code = "B02";
        }
        String idno = loginUser.getCustId();
        Date d = new Date();
        String time = Long.toString(d.getTime());

        String checkCode = otpAuthService.sha256Hex(code + idno + time);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("timestamp", time);
        map.put("checkCode", checkCode.toUpperCase());
        map.put("code", code);
        map.put("idno", idno);
        String JsonData = JsonUtils.getJson(map);
        try {
            byte[] key = systemParamCacheManager.getValue("PIB", "ONLINE_QA_AESKEY").getBytes();
            result = AESUtils.encrypt(JsonData, key);
        }
        catch (CryptException e) {
            logger.error("getOnlineQA_AesKey throw Exception.", e);
            throw ExceptionUtils.getActionException(e);
        }
        return result;
    }

    /**
     * 加密資訊
     * 
     * @param uid
     * @return
     * @throws ActionException
     */
    private String encrypt(String uid) throws ActionException {
        if (aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        try {
            return aesCipherUtils.encrypt(uid);
        }
        catch (CryptException e) {
            logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
