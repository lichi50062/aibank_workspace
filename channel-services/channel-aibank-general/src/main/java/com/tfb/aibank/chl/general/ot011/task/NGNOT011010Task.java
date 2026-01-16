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
package com.tfb.aibank.chl.general.ot011.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot011.model.NGNOT011010Rq;
import com.tfb.aibank.chl.general.ot011.model.NGNOT011010Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.LineBindStatusResponse;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NGNOT011010Task.java
 * 
 * <p>Description:LINE官方帳號綁定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNOT011010Task extends AbstractAIBankBaseTask<NGNOT011010Rq, NGNOT011010Rs> {

    @Autowired
    private UserResource userResource;
    @Autowired
    private SystemParamCacheManager systemParamCachemanager;

    @Override
    public void validate(NGNOT011010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT011010Rq rqData, NGNOT011010Rs rsData) throws ActionException {

        // Line綁定狀態檢查
        LineBindStatusResponse response = userResource.checkLineBindStatus(getLoginUser().getCustId());
        if (StringUtils.equals("0000", response.getErrorCode())) {
            rsData.setBind(StringUtils.isY(response.getBindStatus()));
            if (!rsData.isBind()) {
                rsData.setApplyUrl(systemParamCachemanager.getValue(AIBankConstants.CHANNEL_NAME, "LINE_APPLY_LINK", "https://liff.line.me/1557519568-w9znEj6R"));
            }
        }
        else {
            // 查詢失敗，導至「共通錯誤頁」
            throw new ActionException("LINEBC", response.getErrorCode(), SeverityType.ERROR, response.getErrorMessage());
        }

    }

}
