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
package com.tfb.aibank.chl.component.mfdarea;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.collections.MapUtils;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)MfdArea.java
 * 
 * <p>Description:基金地區</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdArea implements Serializable {

    private static final long serialVersionUID = -2493121563378051844L;

    /** 基金公司代號 */
    private String orgFundCode;
    /** 基金序號 */
    private String subFundCode;
    /** 地區代碼 */
    private String areaCode;
    /** 地區 */
    private String region;
    /** 地區名稱 key:Locale */
    private Map<String, String> areaNameMap;

    /**
     * 取得「基金地區名稱」
     * 
     * @param locale
     * @return
     */
    public String getAreaName(String locale) {
        if (MapUtils.isEmpty(areaNameMap)) {
            return StringUtils.EMPTY;
        }
        return areaNameMap.get(locale);
    }

    public String getOrgFundCode() {
        return orgFundCode;
    }

    public void setOrgFundCode(String orgFundCode) {
        this.orgFundCode = orgFundCode;
    }

    public String getSubFundCode() {
        return subFundCode;
    }

    public void setSubFundCode(String subFundCode) {
        this.subFundCode = subFundCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Map<String, String> getAreaNameMap() {
        return areaNameMap;
    }

    public void setAreaNameMap(Map<String, String> areaNameMap) {
        this.areaNameMap = areaNameMap;
    }

}
