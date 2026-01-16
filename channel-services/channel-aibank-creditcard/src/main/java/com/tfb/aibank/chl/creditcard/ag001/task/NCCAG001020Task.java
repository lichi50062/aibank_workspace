package com.tfb.aibank.chl.creditcard.ag001.task;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.common.fbsecurity.FBSecurity;
import com.tfb.aibank.chl.creditcard.ag001.model.NCCAG001020Rq;
import com.tfb.aibank.chl.creditcard.ag001.model.NCCAG001020Rs;
import com.tfb.aibank.chl.creditcard.ag001.service.NCCAG001CacheVo;
import com.tfb.aibank.chl.creditcard.ag001.service.NCCAG001Service;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.SecurityResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW013RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW320RRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.DecryptRSAEncodedTextRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.DecryptRSAEncodedTextResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.InsertCardSettingCashPwdRequest;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCardSettingCashPwdRequest;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.type.TxStatusType;

//@formatter:off
/**
* @(#)NCCAG001020Task.java
*
* <p>Description:預借現金密碼設定 結果頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG001020Task extends AbstractAIBankBaseTask<NCCAG001020Rq, NCCAG001020Rs> {
    @Autowired
    protected NCCAG001Service service;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NCCAG001020Rq rqData) throws ActionException {

        doTxConfirmCheck();

        if (StringUtils.isBlank(rqData.getCardKey())) {
            this.addErrorFieldMap("cardKey", I18NUtils.getMessage("validate.cardno.desc", getLocale()));
        }

        if (StringUtils.isBlank(rqData.getCvv())) {
            this.addErrorFieldMap("cvv2", I18NUtils.getMessage("validate.cvv.empty.desc", getLocale()));
        }
        else {
            if (rqData.getCvv().length() != 3) {
                this.addErrorFieldMap("cvv2", I18NUtils.getMessage("validate.cvv.length.desc", getLocale()));
            }
            if (!ValidatorUtility.checkNumeric(rqData.getCvv())) {
                this.addErrorFieldMap("cvv2", I18NUtils.getMessage("validate.cvv.number.desc", getLocale()));
            }
        }

        if (StringUtils.isBlank(rqData.getPinBlock())) {
            this.addErrorFieldMap("pinBlock1", I18NUtils.getMessage("validate.cvv.empty.desc", getLocale()));
        }
        else {
            DecryptRSAEncodedTextRequest request = new DecryptRSAEncodedTextRequest();
            request.setEncodedText(rqData.getPinBlock());
            DecryptRSAEncodedTextResponse response = securityResource.decryptRSAEncodedText(request);

            String pinBlock = response.getRawText();

            if (StringUtils.isBlank(pinBlock)) {
                this.addErrorFieldMap("pinBlock1", I18NUtils.getMessage("validate.pinBlock.empty.desc", getLocale()));
            }
            else {
                if (pinBlock.length() != 4) {
                    this.addErrorFieldMap("pinBlock1", I18NUtils.getMessage("validate.pinBlock.length.desc", getLocale()));
                }
                if (!ValidatorUtility.checkNumeric(pinBlock)) {
                    this.addErrorFieldMap("pinBlock1", I18NUtils.getMessage("validate.pinBlock.number.desc", getLocale()));
                }
            }
        }

    }

    @Override
    public void handle(NCCAG001020Rq rqData, NCCAG001020Rs rsData) throws ActionException {

        AIBankUser aibankuser = getLoginUser();
        NCCAG001CacheVo cache = getCache(NCCAG001Service.NCCAG001_CACHE_KEY, NCCAG001CacheVo.class);

        if (cache == null) {
            throw new ActionException(CommonErrorCode.REDIS_UNAVAIBLE);
        }

        CreditCard applyCard = null;
        for (CreditCard card : cache.getCards()) {
            if (card.getCardKey().equals(rqData.getCardKey())) {
                applyCard = card;
                break;
            }
        }
        if (applyCard == null) {
            throw new ActionException(CommonErrorCode.REDIS_UNAVAIBLE);
        }

        /** 解密 pinBlock */
        DecryptRSAEncodedTextRequest request = new DecryptRSAEncodedTextRequest();
        request.setEncodedText(rqData.getPinBlock());
        DecryptRSAEncodedTextResponse response = securityResource.decryptRSAEncodedText(request);

        /** 再以 FBSecurity 加密 */
        String pwd = FBSecurity.encrypt(response.getRawText());

        InsertCardSettingCashPwdRequest addLog = new InsertCardSettingCashPwdRequest();
        addLog.setCustId(aibankuser.getCustId());
        addLog.setUidDup(aibankuser.getUidDup());
        addLog.setUserId(aibankuser.getUserId());
        addLog.setCompanyKind(aibankuser.getCompanyKind());
        addLog.setNameCode(aibankuser.getNameCode());
        addLog.setCardNo(applyCard.getCardNo());
        addLog.setCardCvv2(rqData.getCvv());
        addLog.setBirthday(aibankuser.getBirthDay());
        addLog.setHostTxTime(new Date());
        addLog.setTxStatus(TxStatusType.PROCESS.getCode());
        addLog.setTxDate(new Date());
        addLog.setEmail(aibankuser.getEmail());
        addLog.setClientIp(getRequest().getClientIp());
        addLog.setTxSource(TxSourceType.AI_BANK.getCode());

        Integer borrowKey = creditCardResource.insertCardSettingCashPwd(addLog);

        UpdateCardSettingCashPwdRequest updateLog = new UpdateCardSettingCashPwdRequest();
        updateLog.setBorrowKey(borrowKey);

        Date txDate = new Date();
        CEW320RRequest cew320RRequest = new CEW320RRequest();
        cew320RRequest.setVitxtp("M");
        cew320RRequest.setVicdno(applyCard.getCardNo());
        cew320RRequest.setVicvv2(rqData.getCvv());
        cew320RRequest.setVicdid(applyCard.getCardHoldId());
        cew320RRequest.setVipwde(pwd);

        try {
            creditCardResource.sendCEW320R(cew320RRequest);

            rsData.setCardName(applyCard.getCardName() + " ···· " + applyCard.getCardNo().substring(12));
            rsData.setHostTxTime(DateFormatUtils.CE_DATETIME_FORMAT.format(txDate));

            updateLog.setTxStatus(TxStatusType.SUCCESS.getCode());
            sendMail(aibankuser, TxStatusType.SUCCESS, txDate, null);
        }
        catch (ServiceException ex) {
            updateLog.setTxStatus(TxStatusType.FAIL.getCode());
            ActionException aex = ExceptionUtils.getActionException(ex);

            updateLog.setTxErrorCode(aex.getErrorCode());
            updateLog.setTxErrorDesc(aex.getErrorDesc());
            updateLog.setTxErrorSystemId(aex.getSystemId());
            sendMail(aibankuser, TxStatusType.FAIL, txDate, aex);
            throw aex;

        }
        finally {
            try {
                creditCardResource.updateCardSettingCashPwd(updateLog);
            }
            catch (ServiceException ex) {
                logger.warn("Update CardSettingCashPwd fail ", ex);
            }
        }

    }

    private void sendMail(AIBankUser aibankuser, TxStatusType txStatus, Date time, ActionException ex) {
        // 2025/08/12 配合X開頭錯誤改為不轉換為EAI01，調整相關判斷
        if (ex != null && (StringUtils.equals("EAI01", ex.getErrorCode()) || StringUtils.startsWith(ex.getErrorCode(), "X"))) {
            txStatus = TxStatusType.UNKNOWN;
        }

        CEW013RRes res = getCEW013RMobileNo(aibankuser.getCustId());

        if (res == null)
            return;

        if (StringUtils.isBlank(res.getEmailAddr())) {
            String mobileNo = res.getMobile();
            if (StringUtils.isBlank(mobileNo))
                return;

            String smsTemplate;

            switch (txStatus) {
            case SUCCESS:
                smsTemplate = "nccag001.sms.succ";
                break;
            case FAIL:
                ;
                smsTemplate = "nccag001.sms.fail";
                break;
            default:
                smsTemplate = "nccag001.sms.unknown";
                break;
            }

            sendResultMsg(I18NUtils.getMessage(smsTemplate, DateFormatUtils.CE_DATETIME_FORMAT.format(new Date())), null, null, mobileNo);
            return;
        }

        HashMap<String, String> params = new HashMap<>();

        String status = "";
        String extStatus = "";
        switch (txStatus) {
        case SUCCESS:
            status = I18NUtils.getMessage("nccag001.notification.mail.succ");
            break;
        case FAIL:
            if (ex != null && StringUtils.isNoneBlank(ex.getErrorCode())) {
                status = I18NUtils.getMessage("nccag001.notification.mail.fail");
                extStatus = "(" + ex.getErrorCode() + ")" + ex.getErrorDesc();
            }
            else {
                status = I18NUtils.getMessage("nccag001.notification.mail.unknow");
            }
            break;
        default:
            status = I18NUtils.getMessage("nccag001.notification.mail.unknow");
            break;
        }

        String subject = I18NUtils.getMessage("nccag001.notification.mail.subject", status);
        String title = I18NUtils.getMessage("nccag001.notification.mail.title", status);
        // String content = I18NUtils.getMessage("nccag001.notification.mail.content", status);

        String timeStr = DateFormatUtils.CE_DATETIME_FORMAT.format(time);
        params.put("status", txStatus.getCode());
        params.put("title", title);
        params.put("subTitle", I18NUtils.getMessage("nccag001.notification.mail.subtitle"));
        params.put("content", I18NUtils.getMessage("nccag001.notification.mail.content", timeStr, status));
        params.put("txstatus", status + extStatus);
        params.put("txtime", timeStr);

        sendTxnResultMail(aibankuser.getCustId(), aibankuser.getUidDup(), aibankuser.getUserId(), aibankuser.getCompanyKind(), subject, res.getEmailAddr(), params);

    }

    private CEW013RRes getCEW013RMobileNo(String custId) {
        try {
            CEW013RRes res = creditCardResource.sendCEW013R(custId);
            if (res != null) {
                return res;
            }
        }
        catch (ServiceException ex) {
            logger.warn("CEW013R Error", ex);
        }
        return null;
    }
}