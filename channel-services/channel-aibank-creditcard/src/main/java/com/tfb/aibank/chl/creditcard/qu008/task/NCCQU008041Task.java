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
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008041Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008041Rs;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008Output;
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
public class NCCQU008041Task extends AbstractAIBankBaseTask<NCCQU008041Rq, NCCQU008041Rs> {
    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008041Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU008041Rq rqData, NCCQU008041Rs rsData) throws ActionException {

        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        cache.setBilledDetailSelect(rqData.getBilledDetail());

        boolean isBillProcess = Objects.isNull(rqData.getIsBillProcess()) ? StringUtils.notEquals(rqData.getSourcePageId(), "030") : rqData.getIsBillProcess();

        NCCQU008Output output = new NCCQU008Output();
        service.getOtherInsInterestSection(output, cache, isBillProcess, this.getLoginUser());
        rsData.setOtherInsInterestSection(output.getOtherInsInterestSection());
        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
