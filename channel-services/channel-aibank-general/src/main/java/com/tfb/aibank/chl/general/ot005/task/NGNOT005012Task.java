package com.tfb.aibank.chl.general.ot005.task;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005012Rq;
import com.tfb.aibank.chl.general.ot005.model.NGNOT005012Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UpdatePwdValidTimeRequest;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT005012Task.java 
* 
* <p>Description:沿用舊密碼</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250331, Benson
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT005012Task extends AbstractAIBankBaseTask<NGNOT005012Rq, NGNOT005012Rs> {

    @Autowired
    UserResource userResource;

    @Override
    public void validate(NGNOT005012Rq rqData) throws ActionException {
        AIBankUser aiBankUser = getLoginUser();

        if (aiBankUser.getCustomerType() != CustomerType.GENERAL) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.CC_MEMBER_NOT_ALLOWED_ACC_ERROR);
        }
    }

    @Override
    public void handle(NGNOT005012Rq rqData, NGNOT005012Rs rsData) throws ActionException {
        AIBankUser aiBankUser = getLoginUser();

        UpdatePwdValidTimeRequest rq = new UpdatePwdValidTimeRequest();
        rq.setCustId(aiBankUser.getCustId());
        rq.setUidDup(aiBankUser.getUidDup());
        rq.setUserId(aiBankUser.getUserId());
        rq.setCompanyKind(aiBankUser.getCompanyKind());
        if (aiBankUser.getCustomerType() == CustomerType.GENERAL) {
            rq.setLoginType("m1");
        }

        logger.debug("=!=====> {} {}", rq.getCustId(), rq.getUidDup());
        try {
            userResource.updatePwdValidTime(rq);
        }
        catch (ServiceException ex) {
            logger.warn("延長舊密碼時限失敗", ex);
        }
    }
    /*
     * 只處理中文
     * @see com.ibm.tw.ibmb.task.AbstractBaseTask#getLocale()
     */
    @Override
    protected Locale getLocale() {
       return Locale.TRADITIONAL_CHINESE;
    }

}
