/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.system.ot015.model.AdCardInfoContainer;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015010Rq;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015010Rs;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015Cache;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015Output;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015User;
import com.tfb.aibank.chl.system.ot015.service.NSTOT015Service;

// @formatter:off
/**
 * @(#)NSTOT015010Task.java
 * 
 * <p>Description:廣告版位 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT015010Task extends AbstractAIBankBaseTask<NSTOT015010Rq, NSTOT015010Rs> {

    @Autowired
    private NSTOT015Service service;

    private NSTOT015Output output = new NSTOT015Output();

    @Override
    public void validate(NSTOT015010Rq rqData) throws ActionException {
        if (StringUtils.isBlank(rqData.getTxn())) {
            throwActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }

        if (StringUtils.isBlank(rqData.getTemplate())) {
            throwActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NSTOT015010Rq rqData, NSTOT015010Rs rsData) throws ActionException {
        NSTOT015Cache cache = getCache(NSTOT015Service.CACHE_KEY, NSTOT015Cache.class);
        if (cache == null) {
            cache = new NSTOT015Cache();
        }

        NSTOT015User user = null;
        if (isLoggedIn()) {
            user = new NSTOT015User(getLoginUser());
        }
        else {
            // 免登
            user = new NSTOT015User(getRequest().getDeviceIxd());
        }

        if (cache.getTxnAdCardMap() != null && cache.getTxnAdCardMap().containsKey(rqData.getTxn())) {
            AdCardInfoContainer cachedContainer = cache.getTxnAdCardMap().get(rqData.getTxn());
            rsData.setAdCardInfo(cachedContainer.getAdCardInfo());
            rsData.setHomepageCard(cachedContainer.getHomepageCard());
            rsData.setFromCache(true);
            return;
        }

        service.getAdCard(user, rqData.getTxn(), rqData.getTemplate(), output);
        AdCardInfoContainer container = new AdCardInfoContainer();
        container.setAdCardInfo(output.getAdCardInfo());
        container.setHomepageCard(output.getHomepageCard());
        if (!cache.getTxnAdCardMap().containsKey(rqData.getTxn())) {
            cache.getTxnAdCardMap().put(rqData.getTxn(), container);
        }

        setCache(NSTOT015Service.CACHE_KEY, cache);

        rsData.setAdCardInfo(output.getAdCardInfo());
        rsData.setHomepageCard(output.getHomepageCard());

    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }

}
