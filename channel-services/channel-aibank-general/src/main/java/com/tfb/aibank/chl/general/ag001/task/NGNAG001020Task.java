/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.ag001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.general.ag001.model.NGNAG001020Rq;
import com.tfb.aibank.chl.general.ag001.model.NGNAG001020Rs;
import com.tfb.aibank.chl.general.ag001.service.NGNAG001Service;
import com.tfb.aibank.chl.type.RemarkContentType;

//@formatter:off
/**
* @(#)NGNAG001020Task.java
* 
* <p>Description:免登速查 - 條款頁 - Task</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NGNAG001020Task extends AbstractAIBankBaseTask<NGNAG001020Rq, NGNAG001020Rs> {

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    @Override
    public void validate(NGNAG001020Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNAG001020Rq rqData, NGNAG001020Rs rsData) throws ActionException {

        // 查詢約定條款
        RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), NGNAG001Service.REMARK_KEY, getLocale());

        rsData.setContractTitle(remarkContent.getTitle());
        rsData.setContractContent(remarkContent.getContent());
    }

}
