/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

import com.tfb.aibank.chl.general.resource.vo.twofactor.AppInfoVo;

//@formatter:off
/**
* @(#)TwoFactorAuthRequest.java
* 
* <p>Description: 雙重驗證登入API Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/26, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class TwoFactorAuthRequest {

    /**
     * 使用者Company鍵值
     */
    private String companyKey;

    /**
     * 使用者User鍵值
     */
    private String userKey;

    /**
     * 推播訊息的表頭
     */
    private String pushTitle;

    /**
     * 推播內容
     */
    private String pushContent;

    /**
     * 雙重驗證 appInfo
     */
    private List<AppInfoVo> appInfo;

    /**
     * @return 使用者Company鍵值
     */
    public String getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            使用者Company鍵值
     */
    public void setCompanyKey(String companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return 使用者User鍵值
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            使用者User鍵值
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    /**
     * @return 推播訊息的表頭
     */
    public String getPushTitle() {
        return pushTitle;
    }

    /**
     * @param pushTitle
     *            推播訊息的表頭
     */
    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle;
    }

    /**
     * @return 推播內容
     */
    public String getPushContent() {
        return pushContent;
    }

    /**
     * @param pushContent
     *            推播內容
     */
    public void setPushContent(String pushContent) {
        this.pushContent = pushContent;
    }
    
    /**
     * 
     * @return
     */
    public List<AppInfoVo> getAppInfo() {
        return appInfo;
    }
    /**
     * 
     * @param appInfo
     */
    public void setAppInfo(List<AppInfoVo> appInfo) {
        this.appInfo = appInfo;
    }
     
    
}
