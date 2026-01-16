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
package com.tfb.aibank.chl.creditcard.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.component.cardtype.CardType;
import com.tfb.aibank.chl.component.cardtype.CardTypeCacheManager;

//@formatter:off
/**
* @(#)NCCUtils.java
* 
* <p>Description:CHL 信用卡(NCC)工具類別</p>
* <p>處理與「邏輯」無關的程式行為</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Service
public class NCCUtils {

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;
    @Autowired
    private CardTypeCacheManager cardTypeCacheManager;

    private NCCUtils() {
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

    /**
     * 取得年份數字
     *
     * @param yearMonthDate
     * @return
     */
    public int getYearValue(String yearMonthDate) {
        Date date = DateUtils.getYearMonthDate(yearMonthDate);
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    /**
     * 取得月份數字
     * 
     * @param yearMonthDate
     * @return
     */
    public Integer getMonthValue(String yearMonthDate) {
        if (StringUtils.isEmpty(yearMonthDate)) {
            return null;
        }
        Date date = DateUtils.getYearMonthDate(yearMonthDate);
        return date != null ? DateUtils.getMonthIntFromDate(date) : null;
    }

    /**
     * 取得從當前日期起往前推幾個月的日期
     * 
     * @param yearMonthDate
     * @param amount
     * @return
     */
    public String getPreviousMonthFromNow(String yearMonthDate, int amount) {
        if (StringUtils.isEmpty(yearMonthDate)) {
            return null;
        }
        Date currentDate = DateUtils.getYearMonthDate(yearMonthDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MONTH, amount);
        return DateUtils.getYearMonthDateStr(calendar.getTime());
    }
}
