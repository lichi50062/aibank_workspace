package com.tfb.aibank.chl.general.qu001.task;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001050Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001050Rs;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001050Service;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
// @formatter:off
/**
 * @(#)NGNQU001050Task.java
 *
 * <p>Description: 首頁-匯率資料區塊</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>匯率資料區塊</li>
 * </ol>
 */
//@formatter:on

@Component
@Scope("prototype")
public class NGNQU001050Task extends AbstractAIBankBaseTask<NGNQU001050Rq, NGNQU001050Rs> {

    @Autowired
    @Qualifier("NGNQU001050Service")
    private NGNQU001050Service service;


    @Override
    public void validate(NGNQU001050Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001050Rq rqData, NGNQU001050Rs rsData) throws ActionException {

        DataInput input = new DataInput();
        input.setLocale(getLocale());
        input.setCustCurList(rqData.getCustCurList());

        DataOutput output = new DataOutput();

        service.getExchangeRate(input, output, getLoginUser());

        setRsData(output, rsData);
    }

    private void setRsData(DataOutput output, NGNQU001050Rs rsData) {
        rsData.setExchangeRates(output.getExchangeRates());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
