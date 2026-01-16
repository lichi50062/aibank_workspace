package com.tfb.aibank.chl.creditcard.ag007.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007030Rq;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007030Rs;
import com.tfb.aibank.chl.creditcard.ag007.model.NCCAG007Output;
import com.tfb.aibank.chl.creditcard.ag007.service.NCCAG007Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG007030Task.java
 * 
 * <p>Description:一鍵綁定行動支付 030 取得持卡人英文姓名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG007030Task extends AbstractAIBankBaseTask<NCCAG007030Rq, NCCAG007030Rs> {

    @Autowired
    private NCCAG007Service service;

    private NCCAG007Output dataOutput = new NCCAG007Output();

    @Override
    public void validate(NCCAG007030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG007030Rq rqData, NCCAG007030Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        service.getEngName(loginUser.getCustId(), dataOutput);

        rsData.setEngName(dataOutput.getEngName());
    }

}
