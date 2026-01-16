package com.tfb.aibank.chl.general.qu006.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNQU006010Rs.java
 * 
 * <p>Description:設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU006010Rs implements RsData {

    /** 功能群組 */
    private List<NGNQU006Data> rootDataList;

    /** 功能節點，key:root menu id */
    private Map<String, List<NGNQU006Data>> menuDataMap;

    public List<NGNQU006Data> getRootDataList() {
        return rootDataList;
    }

    public void setRootDataList(List<NGNQU006Data> rootDataList) {
        this.rootDataList = rootDataList;
    }

    public Map<String, List<NGNQU006Data>> getMenuDataMap() {
        return menuDataMap;
    }

    public void setMenuDataMap(Map<String, List<NGNQU006Data>> menuDataMap) {
        this.menuDataMap = menuDataMap;
    }

}
