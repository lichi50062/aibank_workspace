package com.tfb.aibank.chl.creditcard.resource.dto;

public class RetriveDeviceStatusResponse {

    /** 是否支援快速登入 */
    private boolean isFastLogin;

    /**
     * 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識
     */
    private int pwType;

    private String custId;

    private String userId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /** 登入身份 */
    private Integer loginType;

    /**
     * 公司類型 1: 企業戶 2: 個人戶
     */
    private Integer companyKind;

    /**
     * 九宮格因子
     */
    private String coefficient;

    /**
     * 有設定快登時，開啟APP是否直接登入，0:不直接登入；1:直接登入
     */
    private Integer directEzLoginFlag;

    /**
     * 黑名單裝置 = 1
     */
    private Integer isInBlackList;

    /**
     * 狀態碼
     */
    private Integer status;

    /**
     * 廠牌名稱
     */
    private String marketingName;

    /**
     * 是否綁定FIDO2
     */
    private String isBindFido2;

    /**
     * 信用卡/簽帳金融卡FIDO2綁定類型 0：指紋 1：臉部 2：Android生物辨識
     */
    private int fido2Type;

    public boolean isFastLogin() {
        return isFastLogin;
    }

    public void setFastLogin(boolean fastLogin) {
        isFastLogin = fastLogin;
    }

    public int getPwType() {
        return pwType;
    }

    public void setPwType(int pwType) {
        this.pwType = pwType;
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

    public String getUidDup() {
        return uidDup;
    }

    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public Integer getDirectEzLoginFlag() {
        return directEzLoginFlag;
    }

    public void setDirectEzLoginFlag(Integer directEzLoginFlag) {
        this.directEzLoginFlag = directEzLoginFlag;
    }

    public Integer getIsInBlackList() {
        return isInBlackList;
    }

    public void setIsInBlackList(Integer isInBlackList) {
        this.isInBlackList = isInBlackList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getIsBindFido2() {
        return isBindFido2;
    }

    public void setIsBindFido2(String isBindFido2) {
        this.isBindFido2 = isBindFido2;
    }

    public int getFido2Type() {
        return fido2Type;
    }

    public void setFido2Type(int fido2Type) {
        this.fido2Type = fido2Type;
    }
}
