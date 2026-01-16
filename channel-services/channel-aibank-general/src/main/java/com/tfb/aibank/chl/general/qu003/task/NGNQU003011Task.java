package com.tfb.aibank.chl.general.qu003.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003011Rq;
import com.tfb.aibank.chl.general.qu003.model.NGNQU003011Rs;
import com.tfb.aibank.chl.general.qu003.service.NGNQU003Service;

// @formatter:off
/**
 * @(#)NGNQU003011Task.java
 * 
 * <p>Description:優惠 011  蒐藏操作</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, 
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NGNQU003011Task extends AbstractAIBankBaseTask<NGNQU003011Rq, NGNQU003011Rs> {

    @Autowired
    private NGNQU003Service service;

    @Override
    public void validate(NGNQU003011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NGNQU003011Rq rqData, NGNQU003011Rs rsData) throws ActionException {

        if (StringUtils.isNotBlank(rqData.getPromotionKey())) {

            boolean actionResult = false;

            if(rqData.getActionType() == 1){
                actionResult = service.addFavoriePromotion(getLoginUser(), rqData.getPromotionKey());
            }else{
                actionResult = service.deleteMyFavoritePromotion(getLoginUser(), rqData.getPromotionKey());
            }
            rsData.setActionResult(actionResult);
        }
        
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
