package com.tfb.aibank.chl.general.qu002.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.common.model.ServiceBase;

// @formatter:off
/**
 * @(#)NGNQU002030Rs.java
 * 
 * <p>Description:服務據點 030 搜尋結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/18, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU002030Rs implements RsData {
    private List<ServiceBase> serviceBases;
    /** 是否已綁定 */
    private boolean isBind;

    /** 線上取號連結 */
    private String lineLink;

    /** 查詢結果為ATM */
    private boolean isAtm;

    /**
     * @return the serviceBases
     */
    public List<ServiceBase> getServiceBases() {
        return serviceBases;
    }

    /**
     * @param serviceBases
     *            the serviceBases to set
     */
    public void setServiceBases(List<ServiceBase> serviceBases) {
        this.serviceBases = serviceBases;
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

    public boolean isAtm() {
        return isAtm;
    }

    public void setAtm(boolean atm) {
        isAtm = atm;
    }
}
