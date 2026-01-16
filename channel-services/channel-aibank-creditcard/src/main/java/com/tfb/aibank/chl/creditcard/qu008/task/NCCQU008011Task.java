package com.tfb.aibank.chl.creditcard.qu008.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu008.cache.NCCQU008CacheData;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008011Rq;
import com.tfb.aibank.chl.creditcard.qu008.model.NCCQU008011Rs;
import com.tfb.aibank.chl.creditcard.qu008.service.NCCQU008Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW315RResponse;

// @formatter:off
/**
 * @(#)NCCQU008010Task.java
 * 
 * <p>Description:信用卡分期管理 010 #152檢核</p>
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
public class NCCQU008011Task extends AbstractAIBankBaseTask<NCCQU008011Rq, NCCQU008011Rs> {
    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private NCCQU008Service service;

    @Override
    public void validate(NCCQU008011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU008011Rq rqData, NCCQU008011Rs rsData) throws ActionException {
        NCCQU008CacheData cache = getCache(NCCQU008Service.CACHE_KEY, NCCQU008CacheData.class);
        if (StringUtils.equals("1", rqData.getTypeFlag())) {
            service.queryBillInstallment(getLoginUser(), cache);
            CEW315RResponse cew315Rs = cache.getInstallmentBillRs();
            if (cew315Rs != null && StringUtils.equals(cew315Rs.getStatus(), "A")) {
                rsData.setChangeTabFlag("1");
                return;
            }
            service.checkIsInstallmentBillApply(getLoginUser(), cache);
        }
        else if (StringUtils.equals("2", rqData.getTypeFlag())) {
            service.checkBillTxn(getLoginUser(), cache, getLocale());
        }
        setCache(NCCQU008Service.CACHE_KEY, cache);
    }

}
