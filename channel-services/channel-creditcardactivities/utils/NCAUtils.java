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
package com.tfb.aibank.chl.creditcardactivities.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.component.cardtype.CardType;
import com.tfb.aibank.chl.component.cardtype.CardTypeCacheManager;

// @formatter:off
/**
 * @(#)NCAUtils.java
 * 
 * <p>Description:CHL 信用卡活動(NCA)工具類別</p>
 * <p>處理與「邏輯」無關的程式行為</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NCAUtils {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;
    @Autowired
    private CardTypeCacheManager cardTypeCacheManager;

    private NCAUtils() {
        // not allow constructor NCCUtils class
    }

    /**
     * 從 SYSTEM_PARAM 取值
     * 
     * @param category
     * @param paramKey
     * @return
     */
    public String getValue(String category, String paramKey) {
        return systemParamCacheManager.getValue(category, paramKey);
    }

    /**
     * 取「卡種名稱」
     * 
     * @param cardType
     * @param userLocale
     */
    public String getCardLevelDesc(String cardType, Locale userLocale) {
        CardType cardTypeObj = cardTypeCacheManager.getCardType(cardType, userLocale);
        if (cardTypeObj != null && StringUtils.isNotBlank(cardTypeObj.getCardLevelDesc())) {
            return cardTypeObj.getCardLevelDesc();
        }
        return StringUtils.EMPTY;
    }
}
