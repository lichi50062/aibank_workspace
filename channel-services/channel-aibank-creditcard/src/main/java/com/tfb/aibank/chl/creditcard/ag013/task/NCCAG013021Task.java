package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013020Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013020Rs;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013021Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013021Rs;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import com.tfb.aibank.chl.type.RemarkContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013021Task.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 021 FIDO2 SDK錯誤status log紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG013021Task extends AbstractAIBankBaseTask<NCCAG013021Rq, NCCAG013021Rs> {

    @Autowired
    private NCCAG013Service service;

    @Override
    public void validate(NCCAG013021Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013021Rq rqData, NCCAG013021Rs rsData) throws ActionException {
        logger.info("[FIDO2] FIDO2 SDK Status 錯誤訊息: {}", rqData.getErrorDesc());
    }
}
