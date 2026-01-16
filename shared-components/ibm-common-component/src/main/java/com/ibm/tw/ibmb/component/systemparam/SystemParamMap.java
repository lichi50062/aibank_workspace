/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2012.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.systemparam;

import java.util.Hashtable;
import java.util.Map;

import com.ibm.tw.commons.util.StringUtils;

/**
 * <p>
 * 系統參數MAP
 * </p>
 *
 * @author jeff
 * @version 1.0, Apr 12, 2012
 * @see
 * @since
 */
public class SystemParamMap {

    /** 系統參數MAP */
    private Map<String, String> paramMap = new Hashtable<String, String>();

    /**
     * 加入一筆系統參數
     * 
     * @param entity
     */
    public void add(SystemParam entity) {

        String key = StringUtils.trim(entity.getParamKey());
        String value = StringUtils.trimToEmptyEx(entity.getParamValue());

        paramMap.put(key, value);
    }

    /**
     * 根據參數名稱取得參數設定值
     * 
     * @param attrName
     * @return
     */
    public String getValue(String key) {
        return paramMap.get(key);
    }

    /**
     * 重設某參數值
     * 
     * @param attrName
     * @return
     */
    public String setValue(String key, String value) {
        return paramMap.put(key, value);
    }
}
