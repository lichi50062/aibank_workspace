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
package com.tfb.aibank.chl.system.ot006.task;

import java.util.List;
import java.util.function.Predicate;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.system.ot006.model.NSTOT006010Rq;
import com.tfb.aibank.chl.system.ot006.model.NSTOT006010Rs;

//@formatter:off
/**
* @(#)NSTOT006010Task.java
* 
* <p>Description:未持有信用卡錯誤頁</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT006010Task extends AbstractAIBankBaseTask<NSTOT006010Rq, NSTOT006010Rs> {

    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;

    @Override
    public void validate(NSTOT006010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT006010Rq rqData, NSTOT006010Rs rsData) throws ActionException {
        Predicate<HomepageCard> predicate = hc -> StringUtils.equals("NO_CREDITCARD", hc.getCardUsedTaskId()) && StringUtils.equals("TASK_CARD", hc.getCardTemplate()) && hc.getTaType() != 1;

        String custId = isLoggedIn() ? getLoginUser().getCustId() : "";

        List<HomepageCard> homepageCards = homepageCardCacheManager.getMulHomePageCardByPredicate(predicate, custId);
        if (CollectionUtils.isNotEmpty(homepageCards)) {
            HomepageCard homepageCard = homepageCards.get(0);
            rsData.setHomePageCardBg(homepageCard.getCardBg());
            rsData.setHomePageCardDesc(homepageCard.getCardDesc());
            rsData.setHomePageCardTarget(homepageCard.getCardTarget());
            rsData.setCardEvent(homepageCard.getCardEvent());
        }
    }

}
