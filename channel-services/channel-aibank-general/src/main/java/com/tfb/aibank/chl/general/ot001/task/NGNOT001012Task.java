package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001012Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001012Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UpdatePwdValidTimeRequest;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.session.type.CustomerType;

//@formatter:off
/**
* @(#)NGNOT001012Task.java 
* 
* <p>Description:沿用舊密碼</p>
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
public class NGNOT001012Task extends AbstractAIBankBaseTask<NGNOT001012Rq, NGNOT001012Rs> {

    @Autowired
    UserResource userResource;

    @Override
    public void validate(NGNOT001012Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001012Rq rqData, NGNOT001012Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001012 start====");

        AIBankUser aiBankUser = getLoginUser();

        UpdatePwdValidTimeRequest rq = new UpdatePwdValidTimeRequest();
        rq.setCustId(aiBankUser.getCustId());
        rq.setUidDup(aiBankUser.getUidDup());
        rq.setUserId(aiBankUser.getUserId());
        rq.setCompanyKind(aiBankUser.getCompanyKind());
        if (aiBankUser.getCustomerType() == CustomerType.GENERAL) {
            rq.setLoginType("m1");
        }
        else {
            rq.setLoginType("m2");
        }

        logger.debug("=!=====> {} {}", rq.getCustId(), rq.getUidDup());
        try {
            userResource.updatePwdValidTime(rq);
        }
        catch (ServiceException ex) {
            logger.warn("延長舊密碼時限失敗", ex);
        }
    }

}
