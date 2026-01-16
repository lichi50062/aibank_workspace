package com.tfb.aibank.chl.creditcard.qu011.task;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011011Rq;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011011Rs;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011CacheData;
import com.tfb.aibank.chl.creditcard.qu011.model.NCCQU011Output;
import com.tfb.aibank.chl.creditcard.qu011.service.NCCQU011Service;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU011011Task.java
 * 
 * <p>Description:好多金總覧 011 查看更多</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU011011Task extends AbstractAIBankBaseTask<NCCQU011011Rq, NCCQU011011Rs> {

    @Autowired
    private NCCQU011Service service;

    private NCCQU011Output dataOutput = new NCCQU011Output();

    @Override
    public void validate(NCCQU011011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU011011Rq rqData, NCCQU011011Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        NCCQU011CacheData cache = this.getCache(NCCQU011Service.CACHE_KEY, NCCQU011CacheData.class);

        // 發查電文(VB0052)取得好多金明細
        service.getCostcoDetails(loginUser, dataOutput, cache);
        // 有新增加的交易明細，取出Cache的舊有資料，加上新的資料後進行分組
        if (CollectionUtils.isNotEmpty(dataOutput.getCostcoDetails())) {
            service.setCardName(loginUser, getLocale(), dataOutput.getCostcoDetails());
            cache.getCostcoDetails().addAll(dataOutput.getCostcoDetails());
        }
        rsData.setCostcoDetails(cache.getCostcoDetails());
        rsData.setMontyp(cache.getMontyp());

        this.setCache(NCCQU011Service.CACHE_KEY, cache);

    }

}
