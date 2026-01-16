package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)UpdateCreditCardFidoFlagRequest.java
 * 
 * <p>Description: 信用卡/簽帳金融卡FIDO綁定註記 更新 AIBANK_MB_DEVICE_INFO 表 CREDIT_CARD_FIDO2_FLAG 註記 Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/7, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class UpdateCreditCardFido2FlagRequest {

    /**
     * 使用者ID
     */
    private String custId;

    /**
     * 誤別碼
     */
    private String uidDup;

    /**
     * 使用者代號
     */
    private String userId;

    /**
     * 公司類型
     */
    private Integer companyKind;

    /**
     * 裝置ID
     */
    private String deviceIxd;

    /**
     * 信用卡FIDO2綁定註記 0: 未綁定 1: 已綁定
     */
    private Integer CreditCardFido2Flag;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    public String getDeviceIxd() {
        return deviceIxd;
    }

    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    public Integer getCreditCardFido2Flag() {
        return CreditCardFido2Flag;
    }

    public void setCreditCardFido2Flag(Integer creditCardFido2Flag) {
        CreditCardFido2Flag = creditCardFido2Flag;
    }
}
