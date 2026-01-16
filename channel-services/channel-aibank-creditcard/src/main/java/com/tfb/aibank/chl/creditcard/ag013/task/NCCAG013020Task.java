package com.tfb.aibank.chl.creditcard.ag013.task;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013020Rq;
import com.tfb.aibank.chl.creditcard.ag013.model.NCCAG013020Rs;
import com.tfb.aibank.chl.creditcard.ag013.service.NCCAG013Service;
import com.tfb.aibank.chl.type.RemarkContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013020Task.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 020 同意條款</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG013020Task extends AbstractAIBankBaseTask<NCCAG013020Rq, NCCAG013020Rs> {

    @Autowired
    private NCCAG013Service service;

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NCCAG013020Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG013020Rq rqData, NCCAG013020Rs rsData) throws ActionException {

        // [FIDO2] RemarkContent 取得條款資訊
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), NCCAG013Service.TERMS_REMARK_KEY, getLocale().toString());

        logger.info("remarkContentContent: {}", remarkContent.getContent());
        logger.info("remarkContentTitle: {}", remarkContent.getTitle());

        rsData.setTitle(remarkContent.getTitle());
        rsData.setContent(remarkContent.getContent());
    }
}
