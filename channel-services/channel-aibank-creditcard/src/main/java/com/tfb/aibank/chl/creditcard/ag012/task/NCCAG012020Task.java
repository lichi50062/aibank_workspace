package com.tfb.aibank.chl.creditcard.ag012.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012020Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012020Rs;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 * @(#)NCCAG012020Task.java
 * 
 * <p>Description:信用卡交易設定 020 設定頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/11, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG012020Task extends AbstractAIBankBaseTask<NCCAG012020Rq, NCCAG012020Rs> {

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NCCAG012020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012020Rq rqData, NCCAG012020Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        RemarkContent remarkContentInCountry = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO.getType(), "NCCAG012_020_DOMESTIC", getLocale().toString());
        rsData.setRemarkTitleInCountry(remarkContentInCountry.getTitle());
        rsData.setRemarkContentInCountry(remarkContentInCountry.getContent());
        RemarkContent remarkContentOutCountry = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO.getType(), "NCCAG012_020_FOREIGN", getLocale().toString());
        rsData.setRemarkTitleOutCountry(remarkContentOutCountry.getTitle());
        rsData.setRemarkContentOutCountry(remarkContentOutCountry.getContent());
        rsData.setCardInfo(rqData.getCardInfo());
        cache.setCardInfoForTx(rqData.getCardInfo());
        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
