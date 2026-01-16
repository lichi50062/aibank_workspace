package com.tfb.aibank.chl.creditcard.ag011.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011020Rq;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011020Rs;
import com.tfb.aibank.chl.creditcard.ag011.model.NCCAG011Cache;
import com.tfb.aibank.chl.creditcard.ag011.service.NCCAG011Service;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 * @(#)NCCAG011020Task.java
 * 
 * <p>Description:好市多會費代扣繳申請 020 </p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG011020Task extends AbstractAIBankBaseTask<NCCAG011020Rq, NCCAG011020Rs> {

    /** 交易暫存 */
    private NCCAG011Cache cacheData;

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NCCAG011020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG011020Rq rqData, NCCAG011020Rs rsData) throws ActionException {

        cacheData = getCache(NCCAG011Service.CACHE_KEY, NCCAG011Cache.class);
        // 查詢約定條款
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), NCCAG011Service.REMARK_KEY, getLocale());

        rsData.setContractTitle(remarkContent.getTitle());
        rsData.setContractContent(remarkContent.getContent());

    }

}
