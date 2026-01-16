/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)ValidateWithPasswordRuleRequest.java
* 
* <p>Description:密碼檢核 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class ValidateWithPasswordRuleRequest {

    /** 密碼檢核邏輯 */
    private PassRuleType checkRule;

    /** 使用者ID */
    private String uid;

    /** 使用者代號 */
    private String uuid;

    /** 加密的密碼 */
    private String encodedSecrxt;

    /** 舊的加密的密碼 */
    private String oldEncodedSecrxt;

    /** 確認的加密的密碼 */
    private String confirmedEncodedSecrxt;

    /**
     * E2EE給前端PWD加密時，是否帶入之時間因子
     */
    private Boolean isPwdWithTime;

    /**
     * @return the checkRule
     */
    public PassRuleType getCheckRule() {
        return checkRule;
    }

    /**
     * @param checkRule
     *            the checkRule to set
     */
    public void setCheckRule(PassRuleType checkRule) {
        this.checkRule = checkRule;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the encodedSecrxt
     */
    public String getEncodedSecrxt() {
        return encodedSecrxt;
    }

    /**
     * @param encodedSecrxt
     *            the encodedSecrxt to set
     */
    public void setEncodedSecrxt(String encodedSecrxt) {
        this.encodedSecrxt = encodedSecrxt;
    }

    /**
     * @return the oldEncodedSecrxt
     */
    public String getOldEncodedSecrxt() {
        return oldEncodedSecrxt;
    }

    /**
     * @param oldEncodedSecrxt
     *            the oldEncodedSecrxt to set
     */
    public void setOldEncodedSecrxt(String oldEncodedSecrxt) {
        this.oldEncodedSecrxt = oldEncodedSecrxt;
    }

    /**
     * @return the confirmedEncodedSecrxt
     */
    public String getConfirmedEncodedSecrxt() {
        return confirmedEncodedSecrxt;
    }

    /**
     * @param confirmedEncodedSecrxt
     *            the confirmedEncodedSecrxt to set
     */
    public void setConfirmedEncodedSecrxt(String confirmedEncodedSecrxt) {
        this.confirmedEncodedSecrxt = confirmedEncodedSecrxt;
    }

    /**
     * @return the isPwdWithTime
     */
    public Boolean getIsPwdWithTime() {
        return isPwdWithTime;
    }

    /**
     * @param isPwdWithTime
     *            the isPwdWithTime to set
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
