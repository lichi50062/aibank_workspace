/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import com.tfb.aibank.biz.component.e2ee.PassRuleType;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class ValidateWithPasswordRuleRequest {

    @Schema(description = "密碼檢核邏輯")
    private PassRuleType checkRule;

    @Schema(description = "使用者ID")
    private String uid;

    @Schema(description = "使用者代號")
    private String uuid;

    @Schema(description = "加密的密碼")
    private String encodedSecrxt;

    @Schema(description = "舊的加密的密碼")
    private String oldEncodedSecrxt;

    @Schema(description = "確認的加密的密碼")
    private String confirmedEncodedSecrxt;

    @Schema(description = "是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]")
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
     * @return the encodedSecret
     */
    public String getEncodedSecrxt() {
        return encodedSecrxt;
    }

    /**
     * @param encodedSecret
     *            the encodedSecret to set
     */
    public void setEncodedSecrxt(String encodedSecrxt) {
        this.encodedSecrxt = encodedSecrxt;
    }

    /**
     * @return the oldEncodedSecret
     */
    public String getOldEncodedSecrxt() {
        return oldEncodedSecrxt;
    }

    /**
     * @param oldEncodedSecret
     *            the oldEncodedSecret to set
     */
    public void setOldEncodedSecrxt(String oldEncodedSecrxt) {
        this.oldEncodedSecrxt = oldEncodedSecrxt;
    }

    /**
     * @return the confirmedEncodedSecret
     */
    public String getConfirmedEncodedSecrxt() {
        return confirmedEncodedSecrxt;
    }

    /**
     * @param confirmedEncodedSecret
     *            the confirmedEncodedSecret to set
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
