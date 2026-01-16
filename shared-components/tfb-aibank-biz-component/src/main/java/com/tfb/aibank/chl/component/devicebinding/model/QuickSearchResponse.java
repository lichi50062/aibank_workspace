/**
 * 
 */
package com.tfb.aibank.chl.component.devicebinding.model;

import java.util.Date;

/**
 // @formatter:off
 * @(#)QuickSearchResponse.java
 *
 * <p>Description:[免登速查-狀態資料]</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/1, Marty Pan
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class QuickSearchResponse {

    private String qsearchFlag;

    /** 使用者代號 */
    private String userId;

    /** 使用者所屬戶名代碼 */
    private String nameCode;

    /** 公司統編或身份證字號 */
    private String custId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /**
     * 公司BU類型
     * <ul>
     * <li>1: DBU</li>
     * <li>2: OBU</li>
     * </ul>
     *
     */
    private Integer companyBUType;

    /**
     * 公司類型
     * <ul>
     * <li>1: 企業戶</li>
     * <li>2: 個人戶</li>
     * </ul>
     */
    private Integer companyKind;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 是否為特殊戶或未有信用卡 (沒有信用卡)
     */
    private boolean specialUserOrNoCreditCard;

    /**
     * 是否在 AccountCreditcardCheck 黑名單中 true 是在黑名單中
     */
    private boolean isInAccountCreditcardCheck = true;

    /**
     * Banc 與 400 的生日是否相同 false 表示不相同
     */
    private boolean isSameBirthday = false;

    public boolean haveQuickSearchOn() {
        return "1".equals(qsearchFlag);
    }

    public String getQsearchFlag() {
        return qsearchFlag;
    }

    public void setQsearchFlag(String qsearchFlag) {
        this.qsearchFlag = qsearchFlag;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public Integer getCompanyBUType() {
        return companyBUType;
    }

    public void setCompanyBUType(Integer companyBUType) {
        this.companyBUType = companyBUType;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public boolean isSpecialUserOrNoCreditCard() {
        return specialUserOrNoCreditCard;
    }

    public void setSpecialUserOrNoCreditCard(boolean specialUserOrNoCreditCard) {
        this.specialUserOrNoCreditCard = specialUserOrNoCreditCard;
    }

    public boolean isInAccountCreditcardCheck() {
        return isInAccountCreditcardCheck;
    }

    public void setInAccountCreditcardCheck(boolean inAccountCreditcardCheck) {
        isInAccountCreditcardCheck = inAccountCreditcardCheck;
    }

    public boolean isSameBirthday() {
        return isSameBirthday;
    }

    public void setSameBirthday(boolean sameBirthday) {
        isSameBirthday = sameBirthday;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "QuickSearchResponse [qsearchFlag=" + qsearchFlag + ", userId=" + userId + ", nameCode=" + nameCode + ", custId=" + custId + ", uidDup=" + uidDup + ", companyBUType=" + companyBUType + ", companyKind=" + companyKind + ", userKey=" + userKey + ", companyKey=" + companyKey + ", birthday=" + birthday + ", specialUserOrNoCreditCard=" + specialUserOrNoCreditCard + ", isInAccountCreditcardCheck=" + isInAccountCreditcardCheck + ", isSameBirthday=" + isSameBirthday + "]";
    }
}
