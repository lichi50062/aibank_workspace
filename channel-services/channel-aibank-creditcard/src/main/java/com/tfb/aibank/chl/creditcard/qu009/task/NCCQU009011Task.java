package com.tfb.aibank.chl.creditcard.qu009.task;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu009.cache.NCCQU009CacheData;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009011Rq;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009011Rs;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009DebitCardModel;
import com.tfb.aibank.chl.creditcard.qu009.model.NCCQU009PurchaseModel;
import com.tfb.aibank.chl.creditcard.qu009.service.NCCQU009Service;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.common.type.TransOutAcctType;

// @formatter:off
/**
 * @(#)NCCQU009011Task.java
 * 
 * <p>Description:簽帳金融卡消費明細 011 取資料明細</p>
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
public class NCCQU009011Task extends AbstractAIBankBaseTask<NCCQU009011Rq, NCCQU009011Rs> {

    @Autowired
    private NCCQU009Service service;

    @Autowired
    private UserDataCacheService userAccountService;

    @Override
    public void validate(NCCQU009011Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU009011Rq rqData, NCCQU009011Rs rsData) throws ActionException {
        String tabId = rqData.getTabId();
        // 非最新 tabId ＋1
        if (StringUtils.notEquals(tabId, "-1")) {
            tabId = String.valueOf(ConvertUtils.str2Int(tabId) + 1);
        }

        // 業務辦法-帳號-歸戶帳號-臺外幣帳務總覽-臺幣活存。
        List<TransOutAccount> agreedOutAccs = userAccountService.getTransOutAccounts(getLoginUser(), getLocale(), TransOutAcctType.TW_TRANS_OUT_ACCT_OVERVIEW_TW_SAVING, false);
        List<String> acnos = agreedOutAccs.stream().map(acc -> acc.getAcno()).toList();

        String accNo = rqData.getAccNo();
        if (CollectionUtils.isEmpty(acnos) || !acnos.contains(accNo)) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
        NCCQU009CacheData cache = getCache(NCCQU009Service.CACHE_KEY, NCCQU009CacheData.class);
        Map<String, List<NCCQU009DebitCardModel>> debitCardCacheMap = cache.getDebitCardModelMap();
        if (Objects.isNull(debitCardCacheMap.get(tabId + accNo))) {
            service.getDetailByQueryMonth(tabId, rqData, cache, getLocale());
            setCache(NCCQU009Service.CACHE_KEY, cache);
        }
        Map<String, List<NCCQU009DebitCardModel>> newDebitCardCacheMap = cache.getDebitCardModelMap();
        NCCQU009PurchaseModel purchaseCache = cache.getPurchaseByMonthMap().get(tabId + accNo);

        rsData.setDebitCardModels(newDebitCardCacheMap.get(tabId + accNo));
        rsData.setPurchaseDatas(purchaseCache);

    }

}
