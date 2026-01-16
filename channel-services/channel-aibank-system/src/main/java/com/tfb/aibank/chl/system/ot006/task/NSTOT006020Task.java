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

import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.system.ot006.model.NSTOT006020Rq;
import com.tfb.aibank.chl.system.ot006.model.NSTOT006020Rs;

//@formatter:off
/**
* @(#)NSTOT006010Task.java
* 
* <p>Description:繳費交易不支援信用卡戶錯誤頁</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/09, Yoyo Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NSTOT006020Task extends AbstractAIBankBaseTask<NSTOT006020Rq, NSTOT006020Rs> {

    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;

    @Override
    public void validate(NSTOT006020Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT006020Rq rqData, NSTOT006020Rs rsData) throws ActionException {

        Predicate<HomepageCard> predicate = card -> StringUtils.equals("PAYMENT-LOGIN", card.getCardUsedTaskId()) && StringUtils.equals("TASK_CARD", card.getCardTemplate());
        HomepageCard homepageCard = homepageCardCacheManager.getHomePageCardByPredicate(predicate);

        rsData.setHomePageCardBg(homepageCard.getCardBg());
        rsData.setHomePageCardDesc(homepageCard.getCardDesc());
        rsData.setHomePageCardTarget(homepageCard.getCardTarget());
    }

}
