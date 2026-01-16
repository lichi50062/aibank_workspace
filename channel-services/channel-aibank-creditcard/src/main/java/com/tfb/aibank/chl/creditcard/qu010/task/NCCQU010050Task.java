package com.tfb.aibank.chl.creditcard.qu010.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010050Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010050Rs;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;

// @formatter:off
/**
 * @(#)NCCQU010050Task.java
 * 
 * <p>Description:消費分析 050 搜尋頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU010050Task extends AbstractAIBankBaseTask<NCCQU010050Rq, NCCQU010050Rs> {

    @Autowired
    private NCCQU010Service service;

    @Override
    public void validate(NCCQU010050Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU010050Rq rqData, NCCQU010050Rs rsData) throws ActionException {
        // nothing...
    }
}
