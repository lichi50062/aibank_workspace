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
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.SecurityResource;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot009.model.NSTOT009001Rq;
import com.tfb.aibank.chl.system.ot009.model.NSTOT009001Rs;

// @formatter:off
/**
 * @(#)NSTOT009010Task.java
 * 
 * <p>Description:裝置綁定授權設定</p>
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
public class NSTOT009001Task extends AbstractAIBankBaseTask<NSTOT009001Rq, NSTOT009001Rs> {

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NSTOT009001Rq rqData) throws ActionException {
        doTxConfirmCheck();
    }

    @Override
    public void handle(NSTOT009001Rq rqData, NSTOT009001Rs rsData) throws ActionException {

        AIBankUser user = getLoginUser();

        BindingAuthFlagRequest request = new BindingAuthFlagRequest();
        request.setAction(1);
        request.setCustId(user.getCustId());
        request.setUserId(user.getUserId());
        request.setUidDup(user.getUidDup());
        request.setDeviceIxd(getRequest().getDeviceIxd());
        request.setCompanyKind(user.getCompanyKind());

        // 無卡提款
        if (StringUtils.equals(rqData.getTaskId(), "NDSAG005")) {
            request.setWithdrawalFlag(1);
        }
        // 手機號碼轉帳
        if (StringUtils.equals(rqData.getTaskId(), "NDSAG002")) {
            request.setPhoneTransferFlag(1);
        }
        // 變更轉帳額度
        if (StringUtils.equals(rqData.getTaskId(), "NPSAG006")) {
            request.setRaiseTransferFlag(1);
        }

        BindingAuthFlagResponse response = securityResource.getBindingAuthFlag(request);

        rsData.setStatus(response.getStatus());
    }

}
