package com.tfb.aibank.chl.system.ot004.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.log.MBAccessLog;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004040Rq;
import com.tfb.aibank.chl.system.ot004.model.NSTOT004040Rs;

// @formatter:off
/**
 * @(#)NSTOT004040Task.java
 * 
 * <p>Description: 040 Access Log Memo 紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT004040Task extends AbstractAIBankBaseTask<NSTOT004040Rq, NSTOT004040Rs> {

    @Override
    public void validate(NSTOT004040Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT004040Rq rqData, NSTOT004040Rs rsData) throws ActionException {
        MBAccessLog accessLog = getAccessLog();
        accessLog.setTaskId(rqData.getExecTaskId());
        accessLog.setBusType(StringUtils.substring(accessLog.getTaskId(), 1, 3).toUpperCase()); // 業務別 (交易代碼的第二三碼)
        accessLog.setFuncType(StringUtils.substring(accessLog.getTaskId(), 3, 5).toUpperCase()); // 功能類別 (交易代碼的第四五碼)
        accessLog.setMemo(rqData.getMemo());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
