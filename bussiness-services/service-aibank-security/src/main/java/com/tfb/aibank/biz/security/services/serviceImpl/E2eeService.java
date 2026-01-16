package com.tfb.aibank.biz.security.services.serviceImpl;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.biz.component.e2ee.E2EEUtils;
import com.tfb.aibank.biz.component.e2ee.PassRuleMsg;
import com.tfb.aibank.biz.security.gateway.model.CheckSecuirtyRulesRequest;
import com.tfb.aibank.biz.security.gateway.model.CheckSecuirtyRulesResponse;
import com.tfb.aibank.biz.security.gateway.model.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.biz.security.gateway.model.EncodeWithSecretRequest;
import com.tfb.aibank.biz.security.gateway.model.EncodeWithSecretResponse;
import com.tfb.aibank.biz.security.gateway.model.ValidateWithPasswordRuleRequest;
import com.tfb.aibank.biz.security.gateway.model.ValidateWithPasswordRuleRequestResponse;
import com.tfb.aibank.common.type.E2EEDBKeyType;
import com.tfb.aibank.common.type.E2EEHsmType;
import com.tfb.aibank.component.proxy.CryptoProxy;

@Component
public class E2eeService {

    private E2EEUtils e2eeUtils;

    public E2eeService(E2EEUtils e2eeUtils) {
        this.e2eeUtils = e2eeUtils;
    }

    public String getRsaPublicKxy() throws IOException, ActionException {

        return e2eeUtils.getPublicRSAKey();
    }

    public DecryptRSAEncodedTextResponse decryptRSAEncodedText(String encodedText, Boolean isWithTime) throws ActionException {
        DecryptRSAEncodedTextResponse response = new DecryptRSAEncodedTextResponse();
        String rawData = e2eeUtils.decryptRSAEncodedText(encodedText, isWithTime == null ? false : isWithTime);
        response.setRawText(rawData);
        return response;
    }

    public EncodeWithSecretResponse encodewithPasswordRule(EncodeWithSecretRequest request) throws ActionException {
        EncodeWithSecretResponse response = new EncodeWithSecretResponse();
        E2EEHsmType e2eeHsmType = E2EEHsmType.findbyName(request.getE2eeHsmType());
        String hostSecret = e2eeUtils.encodeWithPasswordRule(e2eeHsmType, request.getUid(), request.getUuid(), request.getEncodedSecret(), request.getNumberlist(), request.getCharList(), request.getIsPwdWithTime());
        response.setHostSecret(hostSecret);
        return response;
    }

    public String getDBAccessKxy() throws ActionException {
        return e2eeUtils.getDBAccessKxy();
    }

    public String getOTPAccessKxy() throws ActionException {
        return e2eeUtils.getOTPAccessKxy();
    }

    public String encryptData(String raw, E2EEDBKeyType keyType) throws ActionException {
        if (keyType == null) {
            keyType = E2EEDBKeyType.DB_KEY;
        }
        return e2eeUtils.eptPublicKey(raw, keyType);
    }

    public String decryptData(String cipher, String charsetName, E2EEDBKeyType keyType) throws ActionException {
        if (keyType == null) {
            keyType = E2EEDBKeyType.DB_KEY;
        }
        if (StringUtils.isBlank(charsetName)) {
            return e2eeUtils.deptPublicKey(cipher, keyType).trim();
        }
        return e2eeUtils.deptPublicKey(cipher, CryptoProxy.getCharset(charsetName), keyType).trim();
    }

    public CheckSecuirtyRulesResponse checkSecuirtyRules(CheckSecuirtyRulesRequest request) throws ActionException {
        CheckSecuirtyRulesResponse response = new CheckSecuirtyRulesResponse();
        int status = e2eeUtils.checkSecuirtyRules(request.getUid(), request.getUuid(), request.getEncodedSecret(), request.getOldEncodedSecret(), request.getIsPwdWithTime());
        response.setStatus(status);
        return response;
    }

    public ValidateWithPasswordRuleRequestResponse validateWithPasswordRule(ValidateWithPasswordRuleRequest request) throws ActionException {
        ValidateWithPasswordRuleRequestResponse response = new ValidateWithPasswordRuleRequestResponse();

        PassRuleMsg passRuleMsg = e2eeUtils.validateWithPasswordRule(request.getCheckRule(), request.getUid(), request.getUuid(), request.getEncodedSecrxt(), request.getOldEncodedSecrxt(), request.getConfirmedEncodedSecrxt(), null, null, request.getIsPwdWithTime());
        response.setPassRuleMsg(passRuleMsg);

        return response;
    }

    public boolean isPasswordAdvancedEnable(String companyUid) {
        return e2eeUtils.isPasswordAdvancedEnable(companyUid);
    }

    public boolean isCCUserLoginWithNewPassword(String userPwdFlg, String companyUid) {
        return e2eeUtils.isCCUserLoginWithNewPassword(userPwdFlg, companyUid);
    }

    public boolean isCCUserNewLoginProcessEnable(String companyUid) {
        return e2eeUtils.isCCUserNewLoginProcessEnable(companyUid);
    }

}
