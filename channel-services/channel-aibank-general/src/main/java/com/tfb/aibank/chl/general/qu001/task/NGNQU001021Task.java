/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.qu001.task;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.qu001.model.DataInput;
import com.tfb.aibank.chl.general.qu001.model.DataOutput;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001021Rq;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001021Rs;
import com.tfb.aibank.chl.general.qu001.model.NGNQU001Cache;
import com.tfb.aibank.chl.general.qu001.model.TopValueProduct;
import com.tfb.aibank.chl.general.qu001.service.NGNQU001Service;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.type.InvestProductType;

//@formatter:off
/**
* @(#).java
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/05, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NGNQU001021Task extends AbstractAIBankBaseTask<NGNQU001021Rq, NGNQU001021Rs> {

    @Autowired
    @Qualifier("NGNQU001Service")
    private NGNQU001Service service;

    @Override
    public void validate(NGNQU001021Rq rqData) throws ActionException {
        NGNQU001Cache cache = getCache(NGNQU001021Task.class.getCanonicalName(), NGNQU001Cache.class);
        boolean validateHasUser = (isLoggedIn() && getLoginUser() != null) || (cache.getQsearchData() != null && cache.getQsearchData().haveQuickSearchOn());
        if (!validateHasUser)
            throw new ActionException("no user to query data", CommonErrorCode.TASK_FIELD_ERROR);
    }

    @Override
    public void handle(NGNQU001021Rq rqData, NGNQU001021Rs rsData) throws ActionException {
        NGNQU001Cache cache = getCache(NGNQU001021Task.class.getCanonicalName(), NGNQU001Cache.class);
        if (cache == null)
            return;

        List<TopValueProduct> topValueProducts = rqData.getTopValueProducts();
        if (CollectionUtils.isEmpty(topValueProducts))
            return;

        DataInput input = new DataInput();
        DataOutput output = new DataOutput();

        output.setQuickSearchData(cache.getQsearchData());
        input.setDbu("DBU".equals(cache.getUserBUType()));
        input.setLocale(getLocale());

        if (cache.getQsearchData() == null) {
            AIBankUser user = getLoginUser();
            input.setAiBankUser(user);
            input.setCustId(user.getCustId());
            input.setNameCode(user.getNameCode());
            input.setUserId(user.getUserId());
        }
        else {
            input.setCustId(output.getQuickSearchData().getCustId());
            input.setNameCode(output.getQuickSearchData().getNameCode());
            input.setUserId(StringUtils.EMPTY);
        }
        topValueProducts.forEach(tvp -> tvp.setRoe(service.getRoeByInvestProductType(input, InvestProductType.findByName(tvp.getInvestProductType()))));
        rsData.setTopValueProducts(topValueProducts);
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
