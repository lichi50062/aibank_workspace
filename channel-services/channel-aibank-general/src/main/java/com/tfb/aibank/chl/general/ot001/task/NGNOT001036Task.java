package com.tfb.aibank.chl.general.ot001.task;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.countryname.IpLocationAndCountryNameCacheManager;
import com.tfb.aibank.chl.general.error.ErrorCode;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001036Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001036Rs;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailData;
import com.tfb.aibank.chl.general.ot001.task.service.LoginMailType;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNOT001036Task.java 
* 
* <p>Description:登入 發送綁定失敗通知s</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230928, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001036Task extends AbstractAIBankBaseTask<NGNOT001036Rq, NGNOT001036Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001036Task.class);

    @Autowired
    UserResource userResource;

    @Autowired
    LoginService loginHelper;

    @Override
    public void validate(NGNOT001036Rq rqData) throws ActionException {

    }

    protected void throwMessageException(String desc) throws ActionException {
        throw new ActionException(ErrorCode.VALIDATE_COMMON_ERROR, desc);
    }

    @Override
    public void handle(NGNOT001036Rq rqData, NGNOT001036Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001036 start====");

        AIBankUser user = this.getLoginUser();

        // 發送通知信 for 生物辨識

        LoginMailData bindingMail = loginHelper.getLoginMailData(LoginMailType.BINDING_FAIL, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), user.getCountryName());
        sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), bindingMail.getSubject(), user.getEmail(), bindingMail.getParams());

        LoginMailData fastLoinMail = loginHelper.getLoginMailData(LoginMailType.BIO_SETTING_FAIL, getRequest().getClientIp(), user.getMarketingName(), getLocale(), null, getRequest().getPlatform() + " " + getRequest().getVersion(), user.getCountryName());
        sendMail(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), fastLoinMail.getSubject(), user.getEmail(), fastLoinMail.getParams());

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
