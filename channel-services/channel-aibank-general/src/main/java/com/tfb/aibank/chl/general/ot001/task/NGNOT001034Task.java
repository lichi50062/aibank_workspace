package com.tfb.aibank.chl.general.ot001.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001034Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001034Rs;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001100RsErrorStatus;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.RemarkContentType;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.OnboardingType;

//@formatter:off
/**
* @(#)NGNOT001034Task.java 
* 
* <p>Description:登入 開啟推播設定</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001034Task extends AbstractAIBankBaseTask<NGNOT001034Rq, NGNOT001034Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001034Task.class);

    @Autowired
    SecurityResource securityResource;

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginService;

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NGNOT001034Rq rqData) throws ActionException {
        logger.debug("==== NGNOT001034 start====");
    }

    @Override
    public void handle(NGNOT001034Rq rqData, NGNOT001034Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001034 start====");
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), "NGNOT001_034_LINEBC", getLocale().toString());
        if (remarkContent == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        rsData.setContractTitle(remarkContent.getTitle());
        rsData.setContractContent(remarkContent.getContent());

        RemarkContent note = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO.getType(), "NGNOT001_034_DESC", getLocale().toString());
        if (note == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.TERMS_DATA_NOT_FOUND);
        }
        rsData.setNote(note.getContent());
        rsData.setMaskMobilePhone(DataMaskUtil.maskMobile(this.getLoginUser().getMobileNo()));

        String disableFlag = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DISABLE_ONBOARDING_NOTIFICATION");

        rsData.setDisableNotificationSetting(!StringUtils.equals("N", disableFlag));

        AIBankUser user = this.getLoginUser();
        rsData.setOnboardingType(user.getOnboardingType());
        rsData.setShowChangeTip(user.isShowChangeTip());
        rsData.setLoginType(user.getCustomerType().isGeneral() ? "m1" : "m2");

        // 無痛流程
        if (OnboardingType.isTransfer(user.getOnboardingType())) {

            DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
            if (cache == null) {
                throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
            }

            // 6 項是否有任一為需要處理
            rsData.setTransferNeed(cache.isTransferNeed() || cache.getFastLogin() > 0);
            rsData.setBindingStatus(cache.isOtpAuthed());
            if (rqData.isSkipTransfer()) {
                // 全部略過，還是要訊息設定
                cache.setNotificationAll(true);

                // 其他移轉全關閉
                cache.setNotification(0);
                cache.setQuickSearch(0);
                cache.setMotpSetting(0);
                cache.setNoCardwithDraw(0);
                cache.setPhoneTransfer(0);
                cache.setTransferQuota(0);
                setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
            }

            return;
        }

        ActionException err = new ActionException(AIBankErrorCode.FIDO_SDK_ERROR);
        NGNOT001100RsErrorStatus errorStatus = new NGNOT001100RsErrorStatus();
        errorStatus.setSys(err.getSystemId());
        errorStatus.setCode(err.getErrorCode());
        errorStatus.setDesc(err.getErrorDesc());
        rsData.setErrorStatus(errorStatus);

        rsData.setAlreadySkipBinding(rqData.isAlreadySkipBinding());

        String fidoPortalUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_SDK_URL");
        rsData.setFidoPortalUrl(fidoPortalUrl);

        String businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
        rsData.setBusinessNo(businessNo);

        try {
            // 啟動交易安控驗證
            super.initTxSecurity(I18NUtils.getMessage("ngn.ot001.otp.taskname"));
        }
        catch (ActionException ex) {
            logger.error("", ex);
        }

    }

    protected void sendMail(String custId, String uidDup, String userId, int companyKind, String subject, String mailTo, Map<String, String> params) {
        if (StringUtils.isNotBlank(mailTo)) {
            sendTxnResultMail(custId, uidDup, userId, companyKind, subject, mailTo, params);
        }
    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }

}
