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
package com.tfb.aibank.chl.system.ot010.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NSTOT010010Rs.java
* 
* <p>Description:SSO 登入驗證機制 - 產生 Token</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT010010Rs implements RsData {

    /** 開啟的 URL */
    private String authUrl;

    /**
     * 開啟方式
     */
    private String openType;

    /**
     * Header顯示方式
     */
    private String moduleType;

    /**
     * 內崁參數
     */
    private String moduleParam;

    /**
     * 網域白名單
     */
    private List<String> whiteListWhenOpenUrl;

    /**
     * 抬頭
     */
    private String title;

    /**
     * @return the authUrl
     */
    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * @param authUrl
     *            the authUrl to set
     */
    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl;
    }

    /**
     * @return the openType
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * @param openType
     *            the openType to set
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * @return the moduleType
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * @param moduleType
     *            the moduleType to set
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * @return the whiteListWhenOpenUrl
     */
    public List<String> getWhiteListWhenOpenUrl() {
        return whiteListWhenOpenUrl;
    }

    /**
     * @param whiteListWhenOpenUrl
     *            the whiteListWhenOpenUrl to set
     */
    public void setWhiteListWhenOpenUrl(List<String> whiteListWhenOpenUrl) {
        this.whiteListWhenOpenUrl = whiteListWhenOpenUrl;
    }

    /**
     * @return the moduleParam
     */
    public String getModuleParam() {
        return moduleParam;
    }

    /**
     * @param moduleParam
     *            the moduleParam to set
     */
    public void setModuleParam(String moduleParam) {
        this.moduleParam = moduleParam;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

}
