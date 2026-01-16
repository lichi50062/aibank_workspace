package com.tfb.aibank.chl.creditcard.ag012.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012040Rq;
import com.tfb.aibank.chl.creditcard.ag012.model.NCCAG012040Rs;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 * @(#)NCCAG012040Task.java
 * 
 * <p>Description:信用卡交易設定 040 條款頁</p>
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
public class NCCAG012040Task extends AbstractAIBankBaseTask<NCCAG012040Rq, NCCAG012040Rs> {

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NCCAG012040Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG012040Rq rqData, NCCAG012040Rs rsData) throws ActionException {
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS, "NCCAG012_040_AGREEMENT", getLocale());
        rsData.setRemarkTitle(remarkContent.getTitle());
        rsData.setRemarkContent(remarkContent.getContent());
    }

}
