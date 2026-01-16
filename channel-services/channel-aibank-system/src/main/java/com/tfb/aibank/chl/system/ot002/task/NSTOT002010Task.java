package com.tfb.aibank.chl.system.ot002.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot002.model.NSTOT002010Rq;
import com.tfb.aibank.chl.system.ot002.model.NSTOT002010Rs;
import com.tfb.aibank.chl.system.ot002.model.NSTOT002Output;
import com.tfb.aibank.chl.system.ot002.service.NSTOT002Service;

// @formatter:off
/**
 * @(#)NSTOT002010Task.java
 * 
 * <p>Description:元件功能 010 讀取「輔助說明」、「備註」</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT002010Task extends AbstractAIBankBaseTask<NSTOT002010Rq, NSTOT002010Rs> {

    @Autowired
    private NSTOT002Service service;

    @Override
    public void validate(NSTOT002010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT002010Rq rqData, NSTOT002010Rs rsData) throws ActionException {
        NSTOT002Output dataOutput = new NSTOT002Output();
        service.getRemarkContent(rqData.getType(), rqData.getKey(), getLocale().toString(), dataOutput);

        if (dataOutput.getRemarkContent() != null) {
            rsData.setTitle(dataOutput.getRemarkContent().getTitle());
            rsData.setContent(dataOutput.getRemarkContent().getContent());
            rsData.setVersion(dataOutput.getRemarkContent().getVersion());
        }
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
