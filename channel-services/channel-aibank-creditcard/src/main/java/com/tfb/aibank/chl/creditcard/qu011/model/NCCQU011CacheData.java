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
package com.tfb.aibank.chl.creditcard.qu011.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// @formatter:off
/**
 * @(#)NCCQU011CacheData.java
 * 
 * <p>Description:好多金總覧 Cache 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU011CacheData {

    /** 好多金餘額 (順序固定) */
    private Map<String, NCCQU011CostcoBalance> costcoBalanceMap = new LinkedHashMap<>();

    /** 好多金明細 */
    private List<NCCQU011CostcoDetail> costcoDetails;

    /** 紀錄當前發查VB0052的「查詢月份」 */
    private String montyp;

    public String getMontyp() {
        return montyp;
    }

    public void setMontyp(String montyp) {
        this.montyp = montyp;
    }

    public List<NCCQU011CostcoDetail> getCostcoDetails() {
        return costcoDetails;
    }

    public void setCostcoDetails(List<NCCQU011CostcoDetail> costcoDetails) {
        this.costcoDetails = costcoDetails;
    }

    public Map<String, NCCQU011CostcoBalance> getCostcoBalanceMap() {
        return costcoBalanceMap;
    }

    public void setCostcoBalanceMap(Map<String, NCCQU011CostcoBalance> costcoBalanceMap) {
        this.costcoBalanceMap = costcoBalanceMap;
    }

}
