package com.tfb.aibank.chl.creditcard.qu008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008030Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008030Rs;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;
import com.tfb.aibank.chl.creditcard.qu008.type.NCCQU008StatusType;

// @formatter:off
/**
 * @(#)NCCQU008030Task.java
 * 
 * <p>Description:信用卡分期管理 030 單筆總覽頁</p>
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
public class NCCQU008030Task extends AbstractAIBankBaseTask<NCCQU008030Rq, NCCQU008030Rs> {
    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008030Rq rqData, NCCQU008030Rs rsData) throws ActionException {
        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        service.getBilledDetail(rqData, getLoginUser(), cache, getLocale());

        rsData.setUnBilledDetails(cache.getUnBilledDetails());
        rsData.setUnBilledDetailQueryStatus(cache.getUnBilledDetailQueryStatus());
        rsData.setBilledDetails(cache.getBilledDetails());
        rsData.setBilledDetailQueryStatus(cache.getBilledDetailQueryStatus());
        if (StringUtils.notEquals(rsData.getBilledDetailQueryStatus(), NCCQU008StatusType.QUERY_SUCCESS.getMemo()) && StringUtils.notEquals(rsData.getUnBilledDetailQueryStatus(), NCCQU008StatusType.QUERY_SUCCESS.getMemo())) {
            throw ExceptionUtils.getActionException(ErrorCode.NO_INSTALLMANT_BILL_DETAIL_ERROR);
        }
        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
