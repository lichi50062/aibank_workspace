package com.tfb.aibank.chl.creditcard.ag002.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002020Rq;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002020Rs;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 *
 * <p>Description: 信用卡電子帳單設定 條款頁 </p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG002020Task extends AbstractAIBankBaseTask<NCCAG002020Rq, NCCAG002020Rs> {

    private static final String REMARK_KEY = "NCCAG002_020_CREDIT_CARD_EMAIL_BILL";

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NCCAG002020Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCAG002020Rq rqData, NCCAG002020Rs rsData) throws ActionException {
        // 查詢條款
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), NCCAG002020Task.REMARK_KEY, getLocale());
        rsData.setTermsTitle(remarkContent.getTitle());
        rsData.setTermsContent(remarkContent.getContent());
    }

}
