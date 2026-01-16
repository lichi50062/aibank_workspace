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
package com.tfb.aibank.component.accountgeneralrules;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)AccountGeneralRulesCacheManager.java
 * 
 * <p>Description:帳號通則資料檔 Cache Manager</p>
 * <p>限定 Biz Service 使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class AccountGeneralRulesCacheManager extends AbstractCacheManager {

    @Autowired
    private AccountGeneralRulesResource resource;

    private List<AccountGeneralRules> dataList = new ArrayList<>();

    /**
     * 依產品大類、產品子類，進行正則表示式比對，若查無資料，抛出錯誤訊息「帳號資料處理異常，請與客服人員聯繫」
     * 
     * @param prodCode
     *            產品大類 四碼
     * @param prodSubCode
     *            產品子類 四碼
     * @param userLocale
     *            使用者語系
     * @return
     * @throws ActionException
     */
    public AccountGeneralRules getAccountGeneralRules(String prodCode, String prodSubCode, String userLocale) throws ActionException {
        return getAccountGeneralRules(prodCode + prodSubCode, userLocale);
    }

    /**
     * 依產品大類、產品子類，進行正則表示式比對，若查無資料，抛出錯誤訊息「帳號資料處理異常，請與客服人員聯繫」
     * 
     * @param wholeProdCode
     *            產品大類＋產品子類 共八碼
     * @param locale
     *            使用者語系
     * @return
     * @throws ActionException
     */
    public AccountGeneralRules getAccountGeneralRules(String wholeProdCode, String locale) throws ActionException {
        return dataList.stream().filter(x -> (wholeProdCode.matches(x.getAccountRuleRegexp()) && wholeProdCode.matches(x.getAccountSubRuleRegexp()) && StringUtils.equals(x.getLocale(), locale))).findAny().orElseThrow(() -> ExceptionUtils.getActionException(AIBankErrorCode.ACCOUNT_INFORMATION_PROCESSING_ERROR));
    }

    /**
     * 依產品大類、產品子類，進行正則表示式比對，若查無資料，抛出錯誤訊息「帳號資料處理異常，請與客服人員聯繫」
     * 
     * @param wholeProdCode
     *            產品大類＋產品子類 共八碼
     * @param userLocale
     *            使用者語系
     * @return
     * @throws ActionException
     */
    public AccountGeneralRules getAccountGeneralRules(String wholeProdCode, Locale userLocale) throws ActionException {
        return dataList.stream().filter(x -> (wholeProdCode.matches(x.getAccountRuleRegexp()) && wholeProdCode.matches(x.getAccountSubRuleRegexp()) && StringUtils.equals(x.getLocale(), userLocale.toString()))).findAny().orElseThrow(() -> ExceptionUtils.getActionException(AIBankErrorCode.ACCOUNT_INFORMATION_PROCESSING_ERROR));
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ACCOUNT_GENERAL_RULES_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        this.dataList = resource.getAllAccountGeneralRules();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataList.isEmpty();
    }

}
