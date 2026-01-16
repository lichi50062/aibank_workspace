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
package com.tfb.aibank.chl.general.ag002.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag002.model.NGNAG002010Rq;
import com.tfb.aibank.chl.general.ag002.model.NGNAG002010Rs;
import com.tfb.aibank.chl.general.ag002.service.NGNAG002Service;
import com.tfb.aibank.chl.general.ag002.service.model.NGNAG002GetSettingCardsCondition;
import com.tfb.aibank.chl.general.ag002.service.model.NGNAG002GetSettingCardsResult;

//@formatter:off
/**
* @(#)NGNAG002010Task.java
* 
* <p>Description:首頁牌卡設定 - 設定頁 - Task</p>
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
public class NGNAG002010Task extends AbstractAIBankBaseTask<NGNAG002010Rq, NGNAG002010Rs> {

    @Autowired
    private NGNAG002Service service;

    @Override
    public void validate(NGNAG002010Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNAG002010Rq rqData, NGNAG002010Rs rsData) throws ActionException {

        // 取得畫面牌卡設定資料
        NGNAG002GetSettingCardsCondition condition = new NGNAG002GetSettingCardsCondition();
        condition.setLoginUser(getLoginUser());
        NGNAG002GetSettingCardsResult result = new NGNAG002GetSettingCardsResult();
        service.getSettingCards(condition, result);
        rsData.setCards(result.getCards());
        rsData.setOtherCards(result.getOtherCards());

    }

}
