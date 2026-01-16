package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013012Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013012Rs;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013012Task.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 012 手機系統版本不符引導頁</p>
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
public class NCCAG013012Task extends AbstractAIBankBaseTask<NCCAG013012Rq, NCCAG013012Rs> {

    @Autowired
    private NCCAG013Service service;

    @Override
    public void validate(NCCAG013012Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013012Rq rqData, NCCAG013012Rs rsData) throws ActionException {
        // nothing...
    }
}
