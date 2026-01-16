package com.tfb.aibank.chl.general.qu002.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002010Rs.java
 * 
 * <p>Description:服務據點 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002010Rs implements RsData {
    /** 顯示ATM頁籤 */
    private boolean showAtmTab;
    /** 鄰近分行 */
    private List<ServiceBase> branchs;
    /** 鄰近atm */
    private List<ServiceBase> atms;
    
    /** 是否已綁定 */
    private boolean isBind;

    /** 線上取號連結 */
    private String lineLink;
    
    /** 海外據點連結 */
    private String overseasBranchesLink;

    /**
     * @return the branchs
     */
    public List<ServiceBase> getBranchs() {
        return branchs;
    }
    
    /**
     * @param branchs
     *            the branchs to set
     */
    public void setBranchs(List<ServiceBase> branchs) {
        this.branchs = branchs;
    }
    
    /**
     * @return the atms
     */
    public List<ServiceBase> getAtms() {
        return atms;
    }
    
    /**
     * @param atms
     *            the atms to set
     */
    public void setAtms(List<ServiceBase> atms) {
        this.atms = atms;
    }

    /**
     * @return the showAtmTab
     */
    public boolean isShowAtmTab() {
        return showAtmTab;
    }

    /**
     * @param showAtmTab
     *            the showAtmTab to set
     */
    public void setShowAtmTab(boolean showAtmTab) {
        this.showAtmTab = showAtmTab;
    }

    /**
     * @return the isBind
     */
    public boolean isBind() {
        return isBind;
    }

    /**
     * @param isBind
     *            the isBind to set
     */
    public void setBind(boolean isBind) {
        this.isBind = isBind;
    }

    /**
     * @return the lineLink
     */
    public String getLineLink() {
        return lineLink;
    }

    /**
     * @param lineLink
     *            the lineLink to set
     */
    public void setLineLink(String lineLink) {
        this.lineLink = lineLink;
    }
    
    /**
     * @return the overseasBranchesLink
     */
    public String getOverseasBranchesLink() {
        return overseasBranchesLink;
    }

    /**
     * @param overseasBranchesLink
     *            the overseasBranchesLink to set
     */
    public void setOverseasBranchesLink(String overseasBranchesLink) {
        this.overseasBranchesLink = overseasBranchesLink;
    }

}
