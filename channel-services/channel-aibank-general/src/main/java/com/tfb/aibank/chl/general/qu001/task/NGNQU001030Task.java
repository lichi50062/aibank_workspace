package com.tfb.aibank.chl.general.qu001.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001030Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001030Rs;

// @formatter:off
/**
 * @(#)NGNQU001030Task.java
 *
 * <p>Description: 首頁智能推薦內容區塊</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[智能推薦內容取得]</li>
 * </ol>
 */
//@formatter:on

@Component
@Scope("prototype")
public class NGNQU001030Task extends AbstractAIBankBaseTask<NGNQU001030Rq, NGNQU001030Rs> {

    // @Autowired
    // @Qualifier("NGNQU001Service")
    // private NGNQU001Service service;

    @Override
    public void validate(NGNQU001030Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NGNQU001030Rq rqData, NGNQU001030Rs rsData) throws ActionException {

        // DataInput input = new DataInput();
        DataOutput output = new DataOutput();

        setRsData(output, rsData);
    }

    private void setRsData(DataOutput output, NGNQU001030Rs rsData) {
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
