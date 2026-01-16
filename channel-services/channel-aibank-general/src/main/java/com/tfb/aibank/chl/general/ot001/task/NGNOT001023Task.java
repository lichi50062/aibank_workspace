package com.tfb.aibank.chl.general.ot001.task;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001023Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001023Rs;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.ot001.task.service.PreLoginCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.ChangeUuidAndPinBlockRequest;
import com.tfb.aibank.chl.general.resource.dto.ChangeUuidAndPinBlockResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.common.type.AtferLoginJobType;
import com.tfb.aibank.common.type.ChangRecordItemType;

//@formatter:off
/**
* @(#)NGNOT001023Task.java 
* 
* <p>Description:啟用完成頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001023Task extends AbstractAIBankBaseTask<NGNOT001023Rq, NGNOT001023Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001023Task.class);

    @Autowired
    LoginService loginHelper;

    @Autowired
    SecurityResource securityResource;

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginService;

    @Autowired
    private IpLocationAndCountryNameCacheManager ipLocationAndCountryCacheManager;

    @Override
    public void validate(NGNOT001023Rq rqData) throws ActionException {

        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        populatePreLoginMBAccessLog(cache);

        String userId = "";
        if (AtferLoginJobType.isChangeUseridAndPinBlock(rqData.getChangeType()) || AtferLoginJobType.isChangeUserid(rqData.getChangeType())) {
            userId = loginHelper.decryptText(rqData.getUserId(), false);
            rqData.setUserId(userId);
        }

        // 新使用者代碼
        if ("1".equals(rqData.getChangeType()) || "2".equals(rqData.getChangeType())) {
            if (cache.getUid().contains(rqData.getUserId())) {
                this.addErrorFieldMap("userid", getMessage("com.tfb.aibank.chl.general.ot001.task.error.15"));
                return;
            }

            if (cache.getUuid().equals(rqData.getUserId())) {
                this.addErrorFieldMap("userid", getMessage("com.tfb.aibank.chl.general.ot001.task.error.16"));
                return;
            }
        }

        /** 使用者 */
        if (AtferLoginJobType.isChangeUseridAndPinBlock(rqData.getChangeType()) && (userId.contains(cache.getUuid()) || cache.getUuid().contains(userId))) {
            this.addErrorFieldMap("userid", getMessage("com.tfb.aibank.chl.general.ot001.task.error.15"));
        }
    }
    // 8438 登入前交易寫入 custid, companyKind
    private void populatePreLoginMBAccessLog(PreLoginCache cache) {
        this.populatePreLoginMBAccessLogByCustId(cache.getUid());
        this.populatePreLoginMBAccessLogByCompany(cache.getCompanyKindByLoginType());
        this.populatePreLoginMBAccessLogByUserId(cache.getUuid());
    }

    @Override
    public void handle(NGNOT001023Rq rqData, NGNOT001023Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001023 start====");

        LoginMailType succMailType = null;
        LoginMailType failMailType = null;

        String smsTemplate = null;
        String smsTemplateFail = null;

        // 預設先設為失敗
        rsData.setStatus(1);
        rsData.setChangeType(rqData.getChangeType());

        PreLoginCache cache = getCache(LoginService.PRE_LOGIN_CACHE_KEY, PreLoginCache.class);

        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        ChangeUuidAndPinBlockRequest request = new ChangeUuidAndPinBlockRequest();

        request.setCustId(cache.getUid());

        AtferLoginJobType enumChangeType = AtferLoginJobType.find(rqData.getChangeType());

        switch (enumChangeType) {
        case CHANG_USERID_PINBLOCK:
            // 密碼-rqData
            // 新密碼-rqData
            // 代碼-舊的
            // 新代碼-rqData
            request.setPinblock(rqData.getPinblock());
            request.setNewPinblock(rqData.getPinblock1());
            request.setUserId(cache.getUuid());
            request.setNewUserId(rqData.getUserId());
            request.setChangeItem(ChangRecordItemType.CHANG_USERID_PINBLOCK.getType());

            // 密碼設為新的
            // 代碼設為新的
            cache.setSecret(rqData.getPinblock1());
            cache.setUuid(rqData.getUserId());
            succMailType = LoginMailType.CHANGE_IDPWD_SUCC;
            failMailType = LoginMailType.CHANGE_IDPWD_FAIL;

            smsTemplate = "ngn.ot001.sms.changeprofile.succ";
            smsTemplateFail = "ngn.ot001.sms.changeprofile.fail";
            break;
        case CHANG_USERID:
            // 密碼-rqData
            // 新密碼-rqData (同上個)
            // 代碼-舊的
            // 新代碼-rqData
            request.setPinblock(rqData.getPinblock());
            request.setNewPinblock(rqData.getPinblock());
            request.setUserId(cache.getUuid());
            request.setNewUserId(rqData.getUserId());
            request.setChangeItem(ChangRecordItemType.CHANG_USERID.getType());

            // 密碼取舊的
            // 代碼設為新的
            cache.setSecret(rqData.getPinblock());
            cache.setUuid(rqData.getUserId());

            succMailType = LoginMailType.CHANGE_ID_SUCC;
            failMailType = LoginMailType.CHANGE_ID_FAIL;

            smsTemplate = "ngn.ot001.sms.changeuser.succ";
            smsTemplateFail = "ngn.ot001.sms.changeuser.fail";
            break;
        case CHANG_PINBLOCK:
            // 密碼-rqData
            // 新密碼-rqData (同上個)
            // 代碼-不帶
            // 新代碼-不帶
            request.setPinblock(rqData.getPinblock());
            request.setNewPinblock(rqData.getPinblock1());
            request.setUserId(cache.getUuid());
            request.setNewUserId("");
            request.setChangeItem(ChangRecordItemType.CHANG_PINBLOCK.getType());

            // 密碼設為新的
            // 代碼不用改
            cache.setSecret(rqData.getPinblock1());

            succMailType = LoginMailType.CHANGE_PWD_SUCC;
            failMailType = LoginMailType.CHANGE_PWD_FAIL;

            smsTemplate = "ngn.ot001.sms.changepwd.succ";
            smsTemplateFail = "ngn.ot001.sms.changepwd.fail";

            break;
        default:
            logger.error("變更帳密的 change type 未明={}", rqData.getChangeType());
            throw ExceptionUtils.getActionException(CommonErrorCode.UNKNOWN_EXCEPTION);

        }

        try {

            if (cache.getUid() != null) {
                if (cache.getUid().length() == 8) {
                    request.setCompanyKind(1);
                }
                else {
                    request.setCompanyKind(2);
                }
            }

            ChangeUuidAndPinBlockResponse response = userResource.changeUuidAndPinBlock(request);

            if (response.getStatus() == 0) {

                // 變更成功，設為新的帳密，後續登入用
                cache.setContinueLogin(true);
                setCache(LoginService.PRE_LOGIN_CACHE_KEY, cache);
            }
            else {
                smsTemplate = smsTemplateFail;
            }
            rsData.setStatus(response.getStatus());
            
            if (cache.getEmail() != null && cache.getEmail().size() > 0 && StringUtils.isNotBlank(cache.getEmail().get(0))) {
                LoginMailData loginMailData = loginService.getLoginMailData(succMailType, getRequest().getClientIp(), cache.getMarketingName(), getLocale(), null, null, getCountryName());
                sendMail(cache.getUid(), cache.getUuid(), 2, loginMailData.getSubject(), cache.getEmail(), loginMailData.getParams());
            }
            else {
                if (StringUtils.isNotBlank(cache.getMobileNo())) {
                    int companyKind = 2;
                    if (!"m1".equals(cache.getLoginType())) {
                        companyKind = 3;
                    }
                    sendResultMsg( //
                            cache.getUid(), "0", cache.getUuid(), companyKind, //
                            I18NUtils.getMessage(smsTemplate, DateFormatUtils.CE_DATETIME_FORMAT.format(new Date())), null, null, cache.getMobileNo());
                }
            }

        }
        catch (ServiceException ex) {
            logger.error(ex.getMessage(), ex);

            if (cache.getEmail() != null && cache.getEmail().size() > 0 && StringUtils.isNotBlank(cache.getEmail().get(0))) {
                LoginMailData loginMailData = loginService.getLoginMailData(failMailType, getRequest().getClientIp(), cache.getMarketingName(), getLocale(), null, null, getCountryName());
                sendMail(cache.getUid(), cache.getUuid(), 2, loginMailData.getSubject(), cache.getEmail(), loginMailData.getParams());
            }
            else {
                if (StringUtils.isNotBlank(cache.getMobileNo())) {
                    int companyKind = 2;
                    if (!"m1".equals(cache.getLoginType())) {
                        companyKind = 3;
                    }
                    sendResultMsg( //
                            cache.getUid(), "0", cache.getUuid(), companyKind, //
                            I18NUtils.getMessage(smsTemplateFail, DateFormatUtils.CE_DATETIME_FORMAT.format(new Date())), null, null, cache.getMobileNo());
                }
            }

            logger.error("", ex);
            throw ex;
        }
    }

    protected void sendMail(String custId, String userId, Integer companyKind, String subject, List<String> mailTo, Map<String, String> params) {

        if (mailTo != null) {
            for (String email : mailTo) {
                sendTxnResultMail(custId, "0", userId, companyKind, subject, email, params);
            }
        }

    }

    private String getCountryName() {
        return ipLocationAndCountryCacheManager.getCountryNameByIpLocationAndLocale(this.getClientIp(), getLocale()).getCountryName();
    }

    protected String getMessage(String messageId) {
        return I18NUtils.getMessage(messageId);
    }

    protected String getMessage(String messageId, Object... params) {
        return I18NUtils.getMessage(messageId, params);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
