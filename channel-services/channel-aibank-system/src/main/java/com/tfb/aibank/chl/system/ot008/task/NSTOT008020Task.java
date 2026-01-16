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
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008020Rq;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008020Rs;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 * @(#)NSTOT008020Task.java
 * 
 * <p>Description:裝置綁定完成通知</p>
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
public class NSTOT008020Task extends AbstractAIBankBaseTask<NSTOT008020Rq, NSTOT008020Rs> {

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NSTOT008020Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT008020Rq rqData, NSTOT008020Rs rsData) throws ActionException {

        // 查詢約定條款
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), rqData.getRemark(), getLocale());

        rsData.setContractTitle(remarkContent.getTitle());
        rsData.setContractContent(remarkContent.getContent());

        // 變更轉帳額度
        if (StringUtils.equals(rqData.getTaskNo(), "NPSAG006")) {
            initTxSecurity(I18NUtils.getMessage("nstot008.otp.changeQuota"));
        }

    }

}