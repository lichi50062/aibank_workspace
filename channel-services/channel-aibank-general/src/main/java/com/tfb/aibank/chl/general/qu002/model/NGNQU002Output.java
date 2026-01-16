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
package com.tfb.aibank.chl.general.qu002.model;

import java.util.List;
import java.util.Map;

import com.ibm.tw.ibmb.model.LabelValueBean;
import com.tfb.aibank.chl.component.branch.Branchmap;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002Output.java
 * 
 * <p>Description:服務據點 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/07, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU002Output {

    /** 服務據點清單 */
    private List<Branchmap> branchmapList;

    /** 服務據點物件 */
    private List<ServiceBase> serviceBases;

    /** 選單 */
    private List<LabelValueBean> areaComboItems;

    /** 選單 */
    private Map<String, List<LabelValueBean>> areaDetailComboItemsMap;

    public List<Branchmap> getBranchmapList() {
        return branchmapList;
    }

    public void setBranchmapList(List<Branchmap> branchmapList) {
        this.branchmapList = branchmapList;
    }

    public List<ServiceBase> getServiceBases() {
        return serviceBases;
    }

    public void setServiceBases(List<ServiceBase> serviceBases) {
        this.serviceBases = serviceBases;
    }

    public List<LabelValueBean> getAreaComboItems() {
        return areaComboItems;
    }

    public void setAreaComboItems(List<LabelValueBean> areaComboItems) {
        this.areaComboItems = areaComboItems;
    }

    public Map<String, List<LabelValueBean>> getAreaDetailComboItemsMap() {
        return areaDetailComboItemsMap;
    }

    public void setAreaDetailComboItemsMap(Map<String, List<LabelValueBean>> areaDetailComboItemsMap) {
        this.areaDetailComboItemsMap = areaDetailComboItemsMap;
    }

}
