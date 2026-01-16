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
package com.tfb.aibank.chl.system.ot008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008010Rq;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008010Rs;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)NSTOT008010Task.java
 * 
 * <p>Description:引導裝置綁定頁(不先檢查裝置綁定狀態)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT008010Task extends AbstractAIBankBaseTask<NSTOT008010Rq, NSTOT008010Rs> {
    
    // 檢核本頁安控設定
    @Override
    public void validate(NSTOT008010Rq rqData) throws ActionException {
        this.checkTxSecurity(getTaskId());
    }

    @Override
    public void handle(NSTOT008010Rq rqData, NSTOT008010Rs rsData) throws ActionException {
    }

}
