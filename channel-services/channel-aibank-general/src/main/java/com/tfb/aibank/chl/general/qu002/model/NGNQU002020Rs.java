package com.tfb.aibank.chl.general.qu002.model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.model.LabelValueBean;

// @formatter:off
/**
 * @(#)NGNQU002020Rs.java
 * 
 * <p>Description:服務據點 020 搜尋頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002020Rs implements RsData {

    /** 類型-分行，所在地區-縣市 */
    private List<LabelValueBean> branchArea;
    /** 類型-分行，所在地區-行政區 */
    private Map<String, List<LabelValueBean>> branchAreaDetailMap;

    /** 類型-ATM，所在地區-縣市 */
    private List<LabelValueBean> atmArea;
    /** 類型-ATM，所在地區-行政區 */
    private Map<String, List<LabelValueBean>> atmAreaDetailMap;

    /**
     * @return the branchArea
     */
    public List<LabelValueBean> getBranchArea() {
        return branchArea;
    }

    /**
     * @param branchArea
     *            the branchArea to set
     */
    public void setBranchArea(List<LabelValueBean> branchArea) {
        this.branchArea = branchArea;
    }

    /**
     * @return the branchAreaDetailMap
     */
    public Map<String, List<LabelValueBean>> getBranchAreaDetailMap() {
        return branchAreaDetailMap;
    }

    /**
     * @param branchAreaDetailMap
     *            the branchAreaDetailMap to set
     */
    public void setBranchAreaDetailMap(Map<String, List<LabelValueBean>> branchAreaDetailMap) {
        this.branchAreaDetailMap = branchAreaDetailMap;
    }

    /**
     * @return the atmArea
     */
    public List<LabelValueBean> getAtmArea() {
        return atmArea;
    }

    /**
     * @param atmArea
     *            the atmArea to set
     */
    public void setAtmArea(List<LabelValueBean> atmArea) {
        this.atmArea = atmArea;
    }

    /**
     * @return the atmAreaDetailMap
     */
    public Map<String, List<LabelValueBean>> getAtmAreaDetailMap() {
        return atmAreaDetailMap;
    }

    /**
     * @param atmAreaDetailMap
     *            the atmAreaDetailMap to set
     */
    public void setAtmAreaDetailMap(Map<String, List<LabelValueBean>> atmAreaDetailMap) {
        this.atmAreaDetailMap = atmAreaDetailMap;
    }

}
