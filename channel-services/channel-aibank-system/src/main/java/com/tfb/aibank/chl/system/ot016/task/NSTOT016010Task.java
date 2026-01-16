/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot016.task;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.type.TwoFactorStatusType;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot016.model.NSTOT016010Rq;
import com.tfb.aibank.chl.system.ot016.model.NSTOT016010Rs;
import com.tfb.aibank.chl.system.ot016.model.NSTOT016Output;
import com.tfb.aibank.chl.system.ot016.service.NSTOT016Service;

// @formatter:off
/**
 * @(#)NSTOT016010Task.java
 * 
 * <p>Description:雙重驗證登入API 010 更新資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT016010Task extends AbstractAIBankBaseTask<NSTOT016010Rq, NSTOT016010Rs> {

    @Autowired
    private NSTOT016Service service;

    private NSTOT016Output output = new NSTOT016Output();

    @Override
    public void validate(NSTOT016010Rq rqData) throws ActionException {
        if (StringUtils.isBlank(rqData.getRowId())) {
            throwActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        if (rqData.getAppInfo() == null) {
            throwActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT016010Rq rqData, NSTOT016010Rs rsData) throws ActionException {
        TwoFactorStatusType updateTwoFactorActionType = TwoFactorStatusType.findByType(rqData.getUpdateAction());
        Integer recordKey = Optional.ofNullable(rqData.getRowId()).filter(StringUtils::isNotBlank).map(s -> s.split("_")[1]).map(Integer::parseInt).orElseThrow(() -> ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND));
        service.updateTwoFactorAuth(updateTwoFactorActionType.getType(), recordKey, rqData.getAppInfo(), rqData.isTrustDevice(), output, this.getLocale());
        rsData.setUpdateSuccess(output.getUpdateSuccess());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
