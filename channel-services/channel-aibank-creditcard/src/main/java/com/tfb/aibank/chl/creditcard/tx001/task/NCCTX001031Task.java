package com.tfb.aibank.chl.creditcard.tx001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001031Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001031Rs;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001CacheVo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001Service;

//@formatter:off
/**
* @(#)NCCTX001031Task.java
*
* <p>Description:營業日</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCTX001031Task extends AbstractAIBankBaseTask<NCCTX001031Rq, NCCTX001031Rs> {
    @Autowired
    protected NCCTX001Service service;

    @Override
    public void validate(NCCTX001031Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NCCTX001031Rq rqData, NCCTX001031Rs rsData) throws ActionException {

        rsData.setBusinessTime(service.isBusinessTime());

        NCCTX001CacheVo cache = getCache(NCCTX001Service.NCCTX001_CACHE_KEY, NCCTX001CacheVo.class);
        boolean isOtherBank = !StringUtils.equals(cache.getBankId(), "0122009");

        if (!rsData.isBusinessTime() && isOtherBank) {
            throw new ActionException(ErrorCode.TRANSFER_NOT_IN_BUSINESS_TIME);
        }
    }
}