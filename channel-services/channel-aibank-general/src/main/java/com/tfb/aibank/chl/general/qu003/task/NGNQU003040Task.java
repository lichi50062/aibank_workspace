package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003040Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003040Rs;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003Output;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;

// @formatter:off
/**
 * @(#)NGNQU003040Task.java
 * 
 * <p>Description:優惠 040 我的收藏</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU003040Task extends AbstractAIBankBaseTask<NGNQU003040Rq, NGNQU003040Rs> {

    @Autowired
    private NGNQU003Service service;

    NGNQU003Output output = new NGNQU003Output();

    @Override
    public void validate(NGNQU003040Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNQU003040Rq rqData, NGNQU003040Rs rsData) throws ActionException {

        if (StringUtils.isNotBlank(rqData.getPromotionKey())) {
            service.addFavoriePromotion(getLoginUser(), rqData.getPromotionKey());
        }

        service.getFavoritePromotions(getLoginUser(), output);

        rsData.setPromotions(output.getPromotions());
    }

}
