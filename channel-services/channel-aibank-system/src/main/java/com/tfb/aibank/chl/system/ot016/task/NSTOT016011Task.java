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
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot016.model.NSTOT016011Rq;
import com.tfb.aibank.chl.system.ot016.model.NSTOT016011Rs;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NSTOT016011Task.java
 * 
 * <p>Description:雙重驗證登入API 011 獲取參數</p>
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
public class NSTOT016011Task extends AbstractAIBankBaseTask<NSTOT016011Rq, NSTOT016011Rs> {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    @Override
    public void validate(NSTOT016011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT016011Rq rqData, NSTOT016011Rs rsData) throws ActionException {
        String checkSecondStr = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "TWO_FACTOR_CHECK_SECONDS");
        Integer checkSeconds = Optional.ofNullable(checkSecondStr) // 預設值為5, 格式不為數字為Null -> 預設值 5
                .map(String::trim).filter(s -> !s.isEmpty()).map(s -> {
                    try {
                        return Integer.parseInt(s);
                    }
                    catch (NumberFormatException e) {
                        return null;
                    }
                }).orElse(5);
        rsData.setCheckSeconds(checkSeconds);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
