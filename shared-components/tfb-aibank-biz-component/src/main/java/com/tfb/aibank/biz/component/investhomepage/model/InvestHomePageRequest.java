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
package com.tfb.aibank.biz.component.investhomepage.model;

//@formatter:off
import java.util.List; /**
* @(#)InvestHomePageRequest.java
* 
* <p>Description:投資首頁 - Rq</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/05/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class InvestHomePageRequest {
    private String isLogin;
    private String locale;
    private List<String> orderDatas;
    private String deviceId;
    private String custId;
    private String userId;
    private String nameCode;
    private boolean dbu;
    private boolean haveCreditCard;
    // 是否執行bpm電文 (沒有dbu obu時不送)
    private boolean bpmExecute;
    /** 信用卡會員登入 */
    private boolean ccMemberLogin;

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<String> getOrderDatas() {
        return orderDatas;
    }

    public void setOrderDatas(List<String> orderDatas) {
        this.orderDatas = orderDatas;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNameCode() {
        return nameCode;
    }

    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    public boolean isDbu() {
        return dbu;
    }

    public void setDbu(boolean dbu) {
        this.dbu = dbu;
    }

    public boolean isHaveCreditCard() {
        return haveCreditCard;
    }

    public void setHaveCreditCard(boolean haveCreditCard) {
        this.haveCreditCard = haveCreditCard;
    }

    public boolean isBpmExecute() {
        return bpmExecute;
    }

    public void setBpmExecute(boolean bpmExecute) {
        this.bpmExecute = bpmExecute;
    }

    public boolean isCcMemberLogin() {
        return ccMemberLogin;
    }

    public void setCcMemberLogin(boolean ccMemberLogin) {
        this.ccMemberLogin = ccMemberLogin;
    }
}
