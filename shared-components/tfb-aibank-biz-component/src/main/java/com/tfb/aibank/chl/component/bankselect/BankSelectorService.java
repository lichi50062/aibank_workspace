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
package com.tfb.aibank.chl.component.bankselect;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.bank.BankCacheManager;
import com.tfb.aibank.chl.component.bank.BankData;
import com.tfb.aibank.chl.component.bankselect.model.BankRecord;
import com.tfb.aibank.chl.component.bankselect.model.BankSelector;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)BankSelectorService.java
 * 
 * <p>Description:處理『銀行代碼』選擇元件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class BankSelectorService {

    @Autowired
    private BankCacheManager bankCacheManager;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    /**
     * 設置最近銀行代碼(使用的交易自行決定)
     * 
     * @param bankSelector
     * @param recentlyPayeeBanks
     * @param locale
     */
    public void setRecentlyBanks(BankSelector bankSelector, List<String> recentlyPayeeBanks, Locale locale) {

        List<BankRecord> recentlyBanks = new ArrayList<>();

        if (CollectionUtils.isNotEmpty(recentlyPayeeBanks)) {
            for (String recentlyPayeeBank : recentlyPayeeBanks) {
                BankRecord record = new BankRecord(recentlyPayeeBank, bankCacheManager.getBankName(recentlyPayeeBank, locale));
                recentlyBanks.add(record);
            }
        }

        bankSelector.setRecentlyBanks(sortBanks(recentlyBanks));
    }

    /**
     * 設置熱門銀行代碼
     * 
     * @param bankSelector
     * @param locale
     */
    public void setFavoriteBanks(BankSelector bankSelector, Locale locale) {

        // 熱門銀行代碼
        List<BankRecord> favoriteBanks = new ArrayList<>();
        String favoriteBankIds = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "FAVORITE_BANK_ID");
        String[] favoriteBankIdSplit = StringUtils.split(favoriteBankIds, ",");
        for (String favoriteBank : favoriteBankIdSplit) {
            BankRecord record = new BankRecord(favoriteBank, bankCacheManager.getBankName(favoriteBank, locale));
            favoriteBanks.add(record);
        }

        bankSelector.setFavoriteBanks(sortBanks(favoriteBanks));
    }

    /**
     * 設置全部銀行代碼
     * 
     * @param bankSelector
     * @param locale
     */
    public void setAllBanks(BankSelector bankSelector, Locale locale) {

        // 全部銀行代碼
        List<BankRecord> allBanks = new ArrayList<>();
        List<BankData> banks = bankCacheManager.getAllBanks(locale);
        for (BankData bank : banks) {
            BankRecord record = new BankRecord(bank.getBankId(), bank.getBankName());
            allBanks.add(record);
        }

        bankSelector.setAllBanks(sortBanks(allBanks));
    }

    /**
     * 銀行排序
     * 
     * @param banks
     * @return
     */
    private List<BankRecord> sortBanks(List<BankRecord> banks) {

        if (CollectionUtils.isEmpty(banks)) {
            return banks;
        }

        List<BankRecord> result = new ArrayList<>();

        BankRecord topBank = null;
        List<BankRecord> otherBanks = new ArrayList<>();
        for (BankRecord bankRecord : banks) {
            if (StringUtils.equals(bankRecord.getBankId(), AIBankConstants.TFB_BANK_CODE)) {
                topBank = bankRecord;
            }
            else {
                otherBanks.add(bankRecord);
            }
        }
        if (topBank != null) {
            result.add(topBank);
        }
        if (CollectionUtils.isNotEmpty(otherBanks)) {
            IBUtils.sort(otherBanks, "bankId", false);
            result.addAll(otherBanks);
        }

        return result;
    }

}
