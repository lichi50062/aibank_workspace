package com.tfb.aibank.chl.general.ot001.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001039Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001039Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.ot001.task.service.PatternLockCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UpdatePatternlockRequest;
import com.tfb.aibank.chl.general.resource.dto.UpdatePatternlockResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNOT001039Task.java 
* 
* <p>Description:登入 </p>
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
public class NGNOT001039Task extends AbstractAIBankBaseTask<NGNOT001039Rq, NGNOT001039Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001039Task.class);

    @Autowired
    SecurityResource securityResource;

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginService;

    private PatternLockCache cache;

    @Override
    public void validate(NGNOT001039Rq rqData) throws ActionException {
        logger.debug("==== NGNOT001039 start====");

        if (rqData.isIgnorePatternLockSetting())
            return;

        cache = getCache(LoginService.PATTERN_LOCK_CACHE_KEY, PatternLockCache.class);
        if (cache == null) {
            logger.error("Redis 錯誤，找不到暫存的圖形密碼");
            throw ExceptionUtils.getActionException(ErrorCode.PATTERN_NOT_FOUND);
        }

        AIBankUser aiBankuser = getLoginUser();
        String pinBlock = loginService.encodewithSecret(aiBankuser.getCustId(), aiBankuser.getUserId(), rqData.getPinBlock());

        if (!cache.getPinBlock().equals(pinBlock)) {
            throwMessageException(I18NUtils.getMessage("com.tfb.aibank.chl.general.ot001.pattern.duplicated"));
        }

    }

    protected void throwMessageException(String desc) throws ActionException {
        throw new ActionException(ErrorCode.VALIDATE_COMMON_ERROR, desc);
    }

    @Override
    public void handle(NGNOT001039Rq rqData, NGNOT001039Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001039 start====");

        AIBankUser user = this.getLoginUser();

        UpdatePatternlockResponse response = null;

        if (!rqData.isIgnorePatternLockSetting()) {
            try {
                UpdatePatternlockRequest request = new UpdatePatternlockRequest();

                request.setDeviceUuid(getRequest().getDeviceIxd());
                request.setCustId(getLoginUser().getCustId());
                request.setUidDup(getLoginUser().getUidDup());
                request.setUserId(getLoginUser().getUserId());
                request.setCompanyKind(getLoginUser().getCompanyKind());
                request.setPinblock(cache.getPinBlock());
                request.setCoefficient(cache.getCoefficient());
                response = userResource.updatePatternlock(request);

                // 發送通知信
                if (response != null && response.getStatus() == 0) {
                    LoginMailData fastLoinMail = loginService.getLoginMailData(LoginMailType.PATTERN_SETTING_SUCC, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), user.getCountryName());
                    sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), fastLoinMail.getSubject(), user.getEmail(), fastLoinMail.getParams());

                    updateStatus(LoginService.TRANSFER_SUCC);
                }
                else {
                    LoginMailData fastLoinMail = loginService.getLoginMailData(LoginMailType.PATTERN_SETTING_FAIL, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), user.getCountryName());
                    sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), fastLoinMail.getSubject(), user.getEmail(), fastLoinMail.getParams());
                }

            }
            catch (Exception ex) {
                updateStatus(LoginService.TRANSFER_FAIL);
                // 發送通知信
                try {
                    LoginMailData fastLoinMail = loginService.getLoginMailData(LoginMailType.PATTERN_SETTING_FAIL, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), user.getCountryName());
                    sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), fastLoinMail.getSubject(), user.getEmail(), fastLoinMail.getParams());
                }
                catch (Exception ex1) {
                    logger.warn("發送綁定/快登設定 mail 失敗", ex1);
                }
                logger.error("綁定失敗", ex);
                throw ExceptionUtils.getActionException(ErrorCode.PATTERN_SETTING_FAIL);
            }
        }

    }

    /**
     * 更新 快登 cache 狀態
     * 
     * @param status
     */
    protected void updateStatus(int status) {
        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache != null) {
            cache.setFastLogin(status);
            setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
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
