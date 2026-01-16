package com.tfb.aibank.chl.creditcard.ag012.task;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.ConvertUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag012.cache.NCCAG012CacheData;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012030Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012030Rs;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012LockStatusInfo;
import com.tfb.aibank.chl.creditcard.ag012.service.NCCAG012Service;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 * @(#)NCCAG012030Task.java
 * 
 * <p>Description:信用卡交易設定 030 確認頁</p>
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
public class NCCAG012030Task extends AbstractAIBankBaseTask<NCCAG012030Rq, NCCAG012030Rs> {

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NCCAG012030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012030Rq rqData, NCCAG012030Rs rsData) throws ActionException {
        NCCAG012CacheData cache = getCache(NCCAG012Service.NCCAG012_CACHE_KEY, NCCAG012CacheData.class);
        rsData.setLockStatusInfo(cache.getLockStatusInfo());
        RemarkContent remarkContentInOutCountry = remarkContentCacheManager.getRemarkContent(RemarkContentType.MEMO.getType(), "NCCAG012_030_INFO", getLocale().toString());
        rsData.setRemarkTitleInOutCountry(remarkContentInOutCountry.getTitle());
        rsData.setRemarkContentInOutCountry(remarkContentInOutCountry.getContent());

        NCCAG012LockStatusInfo lockStatus = cache.getLockStatusInfo();
        if ( Boolean.TRUE.equals(lockStatus.getLockActionPayInCountry()) ||  Boolean.TRUE.equals(lockStatus.getLockActionPayOutCountry()) ||  Boolean.TRUE.equals(lockStatus.getLockNotRealInCountry()) ||  Boolean.TRUE.equals(lockStatus.getLockNotRealOutCountry()) ||  Boolean.TRUE.equals(lockStatus.getLockRealInCountry()) || Boolean.TRUE.equals(lockStatus.getLockRealOutCountry())) {
            rsData.setIsGoToRemarkPage(true);
        }
        else if (ConvertUtils.str2BigDecimal(lockStatus.getTxAmtLimitInCountry()).compareTo(BigDecimal.ZERO) > 0 || ConvertUtils.str2BigDecimal(lockStatus.getTxAmtLimitOutCountry()).compareTo(BigDecimal.ZERO) > 0) {
            rsData.setIsGoToRemarkPage(true);
        }
        else {
            rsData.setIsGoToRemarkPage(false);
        }

        setCache(NCCAG012Service.NCCAG012_CACHE_KEY, cache);
    }

}
