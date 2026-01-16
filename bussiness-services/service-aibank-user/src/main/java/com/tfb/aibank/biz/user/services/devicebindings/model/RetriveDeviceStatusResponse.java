/**
 * 
 */
package com.tfb.aibank.biz.user.services.devicebindings.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)RetrieveUserDeviceBindingResponse.java
* 
* <p>Description:取得裝置綁定狀態 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:onclass 
public class RetriveDeviceStatusResponse {

    /** 是否支援快速登入 */
    @Schema(description = "是否支援快速登入")
    private boolean isFastLogin;

    /**
     * 快速登入密碼類型，0:文字密碼；1:手勢密碼；2:指紋；3:臉部；4:Android生物辨識
     */
    @Schema(description = "快速登入方式")
    private int pwType;

    @Schema(description = "身份證字號")
    private String custId;

    @Schema(description = "使用者代號")
    private String userId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 登入身份 */
    @Schema(description = "登入身份")
    private Integer loginType;

    /**
     * 公司類型 1: 企業戶 2: 個人戶
     */
    @Schema(description = "公司")
    private Integer companyKind;

    /**
     * 九宮格因子
     */
    @Schema(description = "九宮格因子")
    private String coefficient;

    /**
     * 有設定快登時，開啟APP是否直接登入，0:不直接登入；1:直接登入
     */
    @Schema(description = "有設定快登時，開啟APP是否直接登入")
    private Integer directEzLoginFlag;

    /**
     * 黑名單裝置 = 1
     */
    @Schema(description = "黑名單裝置")
    private Integer isInBlackList;

    /**
     * 狀態碼
     */
    @Schema(description = "狀態碼")
    private Integer status;

    /**
     * 廠牌名稱
     */
    @Schema(description = "廠牌名稱")
    private String marketingName;

    /**
     * 是否綁定FIDO2
     */
    @Schema(description = "是否綁定FIDO2")
    private boolean isBindFido2;

    /**
     * @return the isFastLogin
     */
    public boolean isFastLogin() {
        return isFastLogin;
    }

    /**
     * @param isFastLogin
     *            the isFastLogin to set
     */
    public void setFastLogin(boolean isFastLogin) {
        this.isFastLogin = isFastLogin;
    }

    /**
     * @return the pwType
     */
    public int getPwType() {
        return pwType;
    }

    /**
     * @param pwType
     *            the pwType to set
     */
    public void setPwType(int pwType) {
        this.pwType = pwType;
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
     * @return the loginType
     */
    public Integer getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    /**
     * @return the directEzLoginFlag
     */
    public Integer getDirectEzLoginFlag() {
        return directEzLoginFlag;
    }

    /**
     * @param directEzLoginFlag
     *            the directEzLoginFlag to set
     */
    public void setDirectEzLoginFlag(Integer directEzLoginFlag) {
        this.directEzLoginFlag = directEzLoginFlag;
    }

    /**
     * @return the isInBlackList
     */
    public Integer getIsInBlackList() {
        return isInBlackList;
    }

    /**
     * @param isInBlackList
     *            the isInBlackList to set
     */
    public void setIsInBlackList(Integer isInBlackList) {
        this.isInBlackList = isInBlackList;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the marketingName
     */
    public String getMarketingName() {
        return marketingName;
    }

    /**
     * @param marketingName
     *            the marketingName to set
     */
    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    /**
     * @return whether FIDO2 is bound
     */
    public boolean isBindFido2() {
        return isBindFido2;
    }

    /**
     * @param bindFido2
     *            whether FIDO2 is bound
     */
    public void setBindFido2(boolean bindFido2) {
        isBindFido2 = bindFido2;
    }
}
