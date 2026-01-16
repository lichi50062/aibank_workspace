package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeed;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeedCommon;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001030Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001030Rs;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001100RsErrorStatus;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.OnboardingType;

//@formatter:off
/**
* @(#)NGNOT001030Task.java 
* 
* <p>Description:快登設定頁</p>
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
public class NGNOT001030Task extends AbstractAIBankBaseTask<NGNOT001030Rq, NGNOT001030Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001030Task.class);

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Autowired
    private LoginService service;

    @Override
    public void validate(NGNOT001030Rq rqData) throws ActionException {
        // 無上行資料，不需做檢核
    }

    @Override
    public void handle(NGNOT001030Rq rqData, NGNOT001030Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001030 start====");
        AIBankUser loginUser = getLoginUser();

        Integer enable = this.getLoginUser().getMbDeviceInfoVo().getEnable();
        if (enable != null && enable == 1) {
            rsData.setAlreadyBindOther(true);
        }

        rsData.setOnboardingType(loginUser.getOnboardingType());
        if (OnboardingType.isTransfer(loginUser.getOnboardingType())) {
            DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
            if (cache == null) {
                throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
            }
            rsData.setMBFastLogin(cache.isMBFastLogin());
        }

        try {
            // 啟動交易安控驗證
            super.initTxSecurity(I18NUtils.getMessage("ngn.ot001.otp.fastlogin"));
        }
        catch (ActionException ex) {
            logger.error(ex.getMessage(), ex);
            rsData.setBypassFastLoginSetting(true);
        }

        ActionException err = new ActionException(AIBankErrorCode.FIDO_SDK_ERROR);
        NGNOT001100RsErrorStatus errorStatus = new NGNOT001100RsErrorStatus();
        errorStatus.setSys(err.getSystemId());
        errorStatus.setCode(err.getErrorCode());
        errorStatus.setDesc(err.getErrorDesc());
        rsData.setErrorStatus(errorStatus);

        String fidoPortalUrl = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_SDK_URL");
        rsData.setFidoPortalUrl(fidoPortalUrl);

        String businessNo = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FIDO_BUSINESS_NO");
        rsData.setBusinessNo(businessNo);

        rsData.setMemberNo(service.getMemberNo(loginUser.getCustId(), getRequest().getDeviceIxd()));

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.tfb.aibank.chl.base.AbstractAIBankBaseTask#getOtpAuthInitData()
     */
    @Override
    protected OtpTxSeed getOtpAuthInitData() {
        return new OtpTxSeedCommon();
    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }
}
