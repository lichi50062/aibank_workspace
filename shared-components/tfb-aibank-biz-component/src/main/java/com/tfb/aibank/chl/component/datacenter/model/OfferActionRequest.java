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
package com.tfb.aibank.chl.component.datacenter.model;

import java.util.Date;
import java.util.List;

//@formatter:off
/**
* @(#)OfferActionRequest.java
* 
* <p>Description:取得情境版位資料 - Requesst</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class OfferActionRequest {
    private String custId;
    private String dup;
    private String sessionId;
    private String deviceIxd;
    private List<String> pdrsonalityTag;
    private List<String> riskTag;
    /** 版位ID */
    private String actionPointId;
    /** 前頁PageID */
    private String prePageId;
    /** 當夜PageID */
    private String curPageId;

    /** 登入時間 */
    private Date loginTime;
    /** 頁面名稱 */
    private String pageName;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getDup() {
        return dup;
    }

    public void setDup(String dup) {
        this.dup = dup;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getDeviceIxd() {
        return deviceIxd;
    }

    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    public List<String> getPdrsonalityTag() {
        return pdrsonalityTag;
    }

    public void setPdrsonalityTag(List<String> pdrsonalityTag) {
        this.pdrsonalityTag = pdrsonalityTag;
    }

    public List<String> getRiskTag() {
        return riskTag;
    }

    public void setRiskTag(List<String> riskTag) {
        this.riskTag = riskTag;
    }

    public String getActionPointId() {
        return actionPointId;
    }

    public void setActionPointId(String actionPointId) {
        this.actionPointId = actionPointId;
    }

    public String getPrePageId() {
        return prePageId;
    }

    public void setPrePageId(String prePageId) {
        this.prePageId = prePageId;
    }

    public String getCurPageId() {
        return curPageId;
    }

    public void setCurPageId(String curPageId) {
        this.curPageId = curPageId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }
}
