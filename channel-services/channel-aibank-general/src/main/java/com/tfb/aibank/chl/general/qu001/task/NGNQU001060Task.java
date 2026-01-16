package com.tfb.aibank.chl.general.qu001.task;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001060Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001060Rs;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NGNQU001060Task.java
 *
 * <p>Description: 首頁-外幣活存優惠利率區塊</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>外幣活存優惠利率區塊</li>
 * </ol>
 */
//@formatter:on

@Component
@Scope("prototype")
public class NGNQU001060Task extends AbstractAIBankBaseTask<NGNQU001060Rq, NGNQU001060Rs> {

    @Autowired
    @Qualifier("NGNQU001Service")
    private NGNQU001Service service;


    @Override
    public void validate(NGNQU001060Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001060Rq rqData, NGNQU001060Rs rsData) throws ActionException {

        DataInput input = new DataInput();
        input.setLocale(getLocale());
        DataOutput output = new DataOutput();

        service.getFxInterestRate(input, output);

        setRsData(output, rsData);
    }



    private void setRsData(DataOutput output, NGNQU001060Rs rsData) {
        rsData.setFxInterestRates(output.getFxInterestRates());
    }


    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
