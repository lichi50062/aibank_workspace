/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot009.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.SecurityResource;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot009.model.NSTOT009000Rq;
import com.tfb.aibank.chl.system.ot009.model.NSTOT009000Rs;

// @formatter:off
/**
 * @(#)NSTOT009000Task.java
 * 
 * <p>Description:裝置綁定檢核頁面初始初始</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, John	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT009000Task extends AbstractAIBankBaseTask<NSTOT009000Rq, NSTOT009000Rs> {

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NSTOT009000Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT009000Rq rqData, NSTOT009000Rs rsData) throws ActionException {

        AIBankUser user = getLoginUser();

        BindingAuthFlagRequest request = new BindingAuthFlagRequest();
        request.setCustId(user.getCustId());
        request.setUserId(user.getUserId());
        request.setUidDup(user.getUidDup());
        request.setCompanyKind(user.getCompanyKind());
        request.setAction(0);
        BindingAuthFlagResponse response = securityResource.getBindingAuthFlag(request);

        // 無卡提款
        if (StringUtils.equals(rqData.getTaskId(), "NDSAG005")) {
            if (response != null && response.getWithdrawalFlag() != null && response.getWithdrawalFlag() == 1) {
                rsData.setAlreadyAuth(true);
                return;
            }
            rsData.setNeedOtp(true);
            initTxSecurity(I18NUtils.getMessage("nstot008.otp.noCardWithdraw"));
        }

        // 變更轉帳額度
        if (StringUtils.equals(rqData.getTaskId(), "NPSAG006")) {
            if (response != null && response.getRaiseTransferFlag() != null && response.getRaiseTransferFlag() == 1) {
                rsData.setAlreadyAuth(true);
                return;
            }
            rsData.setNeedOtp(true);
            initTxSecurity(I18NUtils.getMessage("nstot008.otp.changeQuota"));
        }
    }

}
