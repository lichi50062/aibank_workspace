package com.tfb.aibank.chl.creditcard.qu008.task;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008040Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008040Rs;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;

// @formatter:off
/**
 * @(#)NCCQU008040Task.java
 * 
 * <p>Description:信用卡分期管理 040 方案選項頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU008040Task extends AbstractAIBankBaseTask<NCCQU008040Rq, NCCQU008040Rs> {
    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008040Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU008040Rq rqData, NCCQU008040Rs rsData) throws ActionException {
        rsData.setSourcePageId(rqData.getSourcePageId());
        rsData.setBilledDetailFormPrev(rqData.getBilledDetail());
        rsData.setIsBillProcessFromPrev(rqData.getIsBillProcess());
        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        if (Boolean.TRUE.equals(rqData.getIsGotoBillProcessPage())) {
            rsData.setIsBillProcessAndApply(true);
            return;
        }
        // #9047 [帳單分期]若為"已申請處理中"調整導頁至「方案選擇頁」
        cache.setBilledDetailSelect(rqData.getBilledDetail());

        boolean isBillProcess = Objects.isNull(rqData.getIsBillProcess()) ? StringUtils.notEquals(rqData.getSourcePageId(), "030") : rqData.getIsBillProcess();
        service.getPlanSelection(getLoginUser(), cache, rqData, rsData, isBillProcess);
        if (isBillProcess && cache.getInstallmentBillRs() != null && StringUtils.equals(cache.getInstallmentBillRs().getStatus(), "A")) {
            rsData.setIsBillProcessAndApply(true);
        }
        rsData.setIsBillProcess(isBillProcess);

        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
