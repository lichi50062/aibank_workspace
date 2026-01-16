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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ag002.model.NGNAG002011Rq;
import com.tfb.aibank.chl.general.ag002.model.NGNAG002011Rs;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.UpdateUserHomePageCardRequest;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NGNAG002011Task.java
* 
* <p>Description:首頁牌卡設定 - 更新設定 - Task</p>
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
public class NGNAG002011Task extends AbstractAIBankBaseTask<NGNAG002011Rq, NGNAG002011Rs> {

    @Autowired
    private UserResource userResource;

    @Override
    public void validate(NGNAG002011Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNAG002011Rq rqData, NGNAG002011Rs rsData) throws ActionException {

        // 更新使用者牌卡設定
        updateUserHomePageCard(rqData.getCardIds());

    }

    /**
     * 更新使用者首頁牌卡設定
     * 
     * @param cardIds
     *            牌卡編號
     */
    private void updateUserHomePageCard(List<Integer> cardIds) {

        UpdateUserHomePageCardRequest request = new UpdateUserHomePageCardRequest();
        request.setCardIds(cardIds);

        AIBankUser loginUser = getLoginUser();
        userResource.updateUserHomePageCard(loginUser.getCustId(), loginUser.getUserId(), loginUser.getUidDup(), loginUser.getCompanyKind(), request);
    }

}
