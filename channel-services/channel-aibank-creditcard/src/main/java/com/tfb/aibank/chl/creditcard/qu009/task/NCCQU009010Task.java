package com.tfb.aibank.chl.creditcard.qu009.task;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu009.cache.NCCQU009CacheData;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009010Rq;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009010Rs;
import com.tfb.aibank.chl.creditcard.qu009.service.NCCQU009Service;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.common.type.TransOutAcctType;

// @formatter:off
/**
 * @(#)NCCQU009010Task.java
 * 
 * <p>Description:簽帳金融卡消費明細 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU009010Task extends AbstractAIBankBaseTask<NCCQU009010Rq, NCCQU009010Rs> {
    @Autowired
    private UserDataCacheService userAccountService;

    @Autowired
    private NCCQU009Service service;

    @Override
    public void validate(NCCQU009010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU009010Rq rqData, NCCQU009010Rs rsData) throws ActionException {
        NCCQU009CacheData cache = new NCCQU009CacheData();

        // 業務辦法-帳號-歸戶帳號-臺外幣帳務總覽-臺幣活存。
        List<TransOutAccount> agreedOutAccs = userAccountService.getTransOutAccounts(getLoginUser(), getLocale(), TransOutAcctType.TW_TRANS_OUT_ACCT_OVERVIEW_TW_SAVING, true);

        if (CollectionUtils.isEmpty(agreedOutAccs)) {
            throw ExceptionUtils.getActionException(ErrorCode.NO_SAVING_ACC_ERROR);
        }

        service.setHomePageData(getLoginUser(), agreedOutAccs, cache, rsData, getLocale());

        setCache(NCCQU009Service.CACHE_KEY, cache);
    }

}
