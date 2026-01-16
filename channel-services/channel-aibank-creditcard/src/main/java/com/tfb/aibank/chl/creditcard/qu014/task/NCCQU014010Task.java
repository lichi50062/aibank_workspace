package com.tfb.aibank.chl.creditcard.qu014.task;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu001.model.*;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.qu014.model.NCCQU014010Rq;
import com.tfb.aibank.chl.creditcard.qu014.model.NCCQU014010Rs;
import com.tfb.aibank.chl.creditcard.qu014.model.NCCQU014Input;
import com.tfb.aibank.chl.creditcard.qu014.model.NCCQU014Output;
import com.tfb.aibank.chl.creditcard.qu014.service.NCCQU014Service;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.*;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@formatter:off
/**
* @(#)NCCQU014010Task.java
*
* <p>Description:信用卡循環利率查詢 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/05/24
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU014010Task extends AbstractAIBankBaseTask<NCCQU014010Rq, NCCQU014010Rs> {
    @Autowired
    protected NCCQU014Service service;

    private NCCQU014Input input = new NCCQU014Input();

    private NCCQU014Output output = new NCCQU014Output();

    @Override
    public void validate(NCCQU014010Rq rqData) throws ActionException {
    }

    @Override
    public void handle(NCCQU014010Rq rqData, NCCQU014010Rs rsData) throws ActionException {
            input.setLocale(getLocale());
            service.queryRateInfos(input, output);

            rsData.setInfoGroups(output.getInfoGroups());
            rsData.setInfoCreateTime(output.getInfoCreateTime());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}