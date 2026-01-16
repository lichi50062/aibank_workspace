package com.tfb.aibank.chl.creditcard.ag004.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag004.cache.NCCAG004CacheData;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004020Rq;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004020Rs;
import com.tfb.aibank.chl.creditcard.ag004.service.NCCAG004Service;

//@formatter:off
/**
* @(#)NCCAG004020Task.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG004020Task extends AbstractAIBankBaseTask<NCCAG004020Rq, NCCAG004020Rs> {

    @Override
    public void validate(NCCAG004020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG004020Rq rqData, NCCAG004020Rs rsData) throws ActionException {

        NCCAG004CacheData cache = getCache(NCCAG004Service.NCCAG004_CACHE_KEY, NCCAG004CacheData.class);

        rsData.setCards(cache.getCardList());
        rsData.setEmail(DataMaskUtil.maskEmail(cache.getEmail()));
        if (StringUtils.isBlank(rsData.getEmail())) {
            rsData.setNoEmail(true);
            return;
        }
    }

}
