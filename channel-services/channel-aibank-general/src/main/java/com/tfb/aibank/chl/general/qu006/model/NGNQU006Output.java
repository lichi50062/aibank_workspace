package com.tfb.aibank.chl.general.qu006.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @formatter:off
/**
 * @(#)NGNQU006Output.java
 * 
 * <p>Description:設定 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU006Output {

    /** 「功能群組」資料 */
    private List<NGNQU006Data> rootDataList = new ArrayList<>();
    /** 選單 */
    private Map<String, List<NGNQU006Data>> menuDataMap = new HashMap<>();

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
