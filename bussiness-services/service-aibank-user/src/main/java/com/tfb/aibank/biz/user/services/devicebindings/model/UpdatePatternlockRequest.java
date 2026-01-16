/**
 * 
 */
package com.tfb.aibank.biz.user.services.devicebindings.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UpdateUserDeviceBindingRequest.java
* 
* <p>Description:更新圖形登入綁定資料 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdatePatternlockRequest {

    /** 行動裝置UUID */
    @Schema(description = "行動裝置UUID")
    private String deviceUuid;

    /** 身分證字號 */
    @Schema(description = "身分證字號")
    private String custId;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /** 圖形密碼 */
    @Schema(description = "圖形密碼")
    private String pinblock;

    /** 圖形因子 */
    @Schema(description = "圖形因子")
    private String coefficient;

    /**
     * @return the coefficient
     */
    public String getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient
     *            the coefficient to set
     */
    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * @return the deviceUuid
     */
    public String getDeviceUuid() {
        return deviceUuid;
    }

    /**
     * @param deviceUuid
     *            the deviceUuid to set
     */
    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the pinblock
     */
    public String getPinblock() {
        return pinblock;
    }

    /**
     * @param pinblock
     *            the pinblock to set
     */
    public void setPinblock(String pinblock) {
        this.pinblock = pinblock;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

}
