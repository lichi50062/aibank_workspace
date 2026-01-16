package com.tfb.aibank.chl.creditcard.ag010.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.e2ee.PassRuleMsg;
import com.tfb.aibank.biz.component.e2ee.PassRuleType;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag010.model.NCCAG010011Rq;
import com.tfb.aibank.chl.creditcard.ag010.model.NCCAG010011Rs;
import com.tfb.aibank.chl.creditcard.ag010.model.NCCAG010DataOutput;
import com.tfb.aibank.chl.creditcard.ag010.service.NCCAG010Service;
import com.tfb.aibank.chl.creditcard.resource.SecurityResource;
import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteChangeCcUserPinResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.ValidateWithPasswordRuleRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.ValidateWithPasswordRuleResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.MailParamType;
import com.tfb.aibank.common.type.TxStatusType;

import jakarta.servlet.http.HttpSession;

// @formatter:off
/** 
 * @(#)NCCAG010011Task.java
 * 
 * <p>Description: 變更密碼(信用卡) 確認變更</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG010011Task extends AbstractAIBankBaseTask<NCCAG010011Rq, NCCAG010011Rs> {

    @Autowired
    private NCCAG010Service service;

    @Autowired
    private SecurityResource securityResource;

    private NCCAG010DataOutput dataOutput = new NCCAG010DataOutput();

    @Override
    public void validate(NCCAG010011Rq rqData) throws ActionException {
        if (StringUtils.isAnyBlank(rqData.getEoldpxss(), rqData.getEnewPxss())) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        AIBankUser loginUser = this.getLoginUser();
        PassRuleMsg passRuleMsg = validateWithPasswordRule(loginUser.getCustId(), loginUser.getUserId(), rqData.getEnewPxss());
        switch (passRuleMsg) {
        case COMPARE_UID_ERR:
            this.addErrorFieldMap("newPxss", I18NUtils.getMessage("nccag010.validatore.uid.error"));
            break;
        case COMPARE_UUID_ERR:
            this.addErrorFieldMap("newPxss", I18NUtils.getMessage("nccag010.validatore.uuid.error"));
            break;
        case PASSWORD_BLANK_ERR:
            // 請輸入使用者密碼
            this.addErrorFieldMap("newPxss", I18NUtils.getMessage("nccag010.validatore.blank.error"));
            break;
        case PASSWORD_LENGTH_ERR:
            // 使用者密碼長度須為6~16位
            this.addErrorFieldMap("newPxss", I18NUtils.getMessage("nccag010.validatore.blank.error"));
            break;
        case PASSWORD_ENG_NUM_ERR:
            // 密碼必為英數字混雜
            this.addErrorFieldMap("newPxss", I18NUtils.getMessage("nccag010.validatore.eng.num.error"));
            break;

        default:
            break;
        }
    }

    @Override
    public void handle(NCCAG010011Rq rqData, NCCAG010011Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        try {
            // 執行驗證及變更密碼
            service.sendChangePinBlock(rqData.getEoldpxss(), rqData.getEnewPxss(), loginUser, dataOutput);
            ExecuteChangeCcUserPinResponse response = dataOutput.getResponse();

            if (response != null) {
                if (response.isSuccess()) {
                    // 密碼設定成功
                    rsData.setResult(I18NUtils.getMessage("nccag010.result.sucess"));
                    if (StringUtils.isNotBlank(getLoginUser().getEmail())) {
                        sendSuccessEmailNotify();
                    }
                    else {
                        String smsTxt = I18NUtils.getMessage("nccag010.sms", DateUtils.getDateTimeStr(new Date()), I18NUtils.getMessage("nccag010.mail.success"));
                        sendResultMsg(smsTxt, null, null);
                    }
                    loginUser.getUserData().setTxDate(new Date());
                }
                else if (response.getErrorStatus() != null) {
                    throw ExceptionUtils.getActionException(response.getErrorStatus());
                }
            }
        }
        catch (ActionException ex) {
            logger.error(ex.getMessage(), ex);
            ActionException aex = ExceptionUtils.getActionException(ex);
            // 密碼錯誤，第1次、第2次
            if (StringUtils.equalsAny(aex.getErrorCode(), AIBankErrorCode.PASSWORD_FAIL_COUNT1.getErrorCode(), AIBankErrorCode.PASSWORD_FAIL_COUNT2.getErrorCode())) {
                if (StringUtils.isNotBlank(getLoginUser().getEmail())) {
                    sendFailEmailNotify(aex);
                }
                else {
                    String smsTxt = I18NUtils.getMessage("nccag010.sms", DateUtils.getDateTimeStr(new Date()), I18NUtils.getMessage("nccag010.mail.fail"));
                    sendResultMsg(smsTxt, null, null);
                }
                throw aex;
            }
            // 密碼錯誤達3次
            else if (StringUtils.equals(aex.getErrorCode(), AIBankErrorCode.PASSWORD_FAIL_3COUNT.getErrorCode())) {

                service.executeUserLogout(getLoginUser());

                if (StringUtils.isNotBlank(getLoginUser().getEmail())) {
                    sendFailEmailNotify(aex);
                }
                else {
                    String smsTxt = I18NUtils.getMessage("nccag010.sms", DateUtils.getDateTimeStr(new Date()), I18NUtils.getMessage("nccag010.mail.fail"));
                    sendResultMsg(smsTxt, null, null);
                }

                // 取得目前 session
                HttpSession session = getHttpServletRequest().getSession(false);
                if (session != null) {
                    // 使Session無效，然後解除綁定到它的任何物件。
                    session.invalidate();
                    setLoginUser(null);
                }
                throw aex;
            }
            else {
                aex = ExceptionUtils.getActionException(AIBankErrorCode.PASSWORD_CHANGE_FAIL);
                if (StringUtils.isNotBlank(getLoginUser().getEmail())) {
                    sendFailEmailNotify(aex);
                }
                else {
                    String smsTxt = I18NUtils.getMessage("nccag010.sms", DateUtils.getDateTimeStr(new Date()), I18NUtils.getMessage("nccag010.mail.fail"));
                    sendResultMsg(smsTxt, null, null);
                }
                throw aex;
            }
        }
    }

    private void sendFailEmailNotify(ActionException aex) {
        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();

        // 交易結果
        params.put(MailParamType.TX_STATUS.getCode(), TxStatusType.FAIL.getCode());
        params.put(MailParamType.TXN_NAME.getCode(), I18NUtils.getMessage("nccag010.notification.txnName")); // 變更密碼
        params.put("errorMsg", aex.getErrorCode() + " " + getErrorDesc(aex.getSystemId(), aex.getStatus().getErrorCode()));
        // 交易時間
        params.put(MailParamType.HOST_TX_TIME.getCode(), DateUtils.getDateTimeStr(new Date()));

        // 台北富邦行動銀行變更密碼「失敗」通知
        String subject = I18NUtils.getMessage("nccag010.notification.email.fail.subject");
        String email = getLoginUser().getUserData().getEmails();
        if (StringUtils.isNotBlank(email)) {
            this.sendTxnResultMail(subject, email, params);
        }
    }

    private void sendSuccessEmailNotify() {
        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();
        // 交易結果
        params.put(MailParamType.TX_STATUS.getCode(), TxStatusType.SUCCESS.getCode());
        params.put(MailParamType.TXN_NAME.getCode(), I18NUtils.getMessage("nccag010.notification.txnName")); // 變更密碼
        // 交易時間
        params.put(MailParamType.HOST_TX_TIME.getCode(), DateUtils.getDateTimeStr(new Date()));

        // 台北富邦行動銀行變更密碼「成功」通知
        String subject = I18NUtils.getMessage("nccag010.notification.email.success.subject");
        String email = getLoginUser().getUserData().getEmails();
        if (StringUtils.isNotBlank(email)) {
            this.sendTxnResultMail(subject, email, params);
        }
    }

    public PassRuleMsg validateWithPasswordRule(String custId, String userId, String pinblock) {
        ValidateWithPasswordRuleRequest rqValidateWithPasswordRuleRequest = new ValidateWithPasswordRuleRequest();
        rqValidateWithPasswordRuleRequest.setCheckRule(PassRuleType.CHECK_IS_FIRST_LOGIN);
        rqValidateWithPasswordRuleRequest.setUid(custId);
        rqValidateWithPasswordRuleRequest.setUuid(userId);
        rqValidateWithPasswordRuleRequest.setEncodedSecrxt(pinblock);

        ValidateWithPasswordRuleResponse resposne = securityResource.validateWithPasswordRule(rqValidateWithPasswordRuleRequest);
        return resposne.getPassRuleMsg();
    }

}