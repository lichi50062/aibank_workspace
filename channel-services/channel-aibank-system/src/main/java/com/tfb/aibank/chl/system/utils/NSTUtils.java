package com.tfb.aibank.chl.system.utils;

import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)NSTUtils.java
* 
* <p>Description:CHL 系統(NST)工具類別</p>
* <p>處理與「邏輯」無關的程式行為</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Edward Tien
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NSTUtils {

    /**
     * 這是一個範例 method
     * 
     * @param input
     *            傳入的資料
     * @param output
     *            回傳的資料
     */
    public boolean checkIsY(String str) {
        // TODO: 處理與邏輯無關的程式行為
        return StringUtils.isY(str);
    }
}
