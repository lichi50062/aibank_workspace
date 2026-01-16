package com.tfb.aibank.chl.creditcard.resource.dto;

// @formatter:off
/**
 * @(#)QueryUserInfoFidoResponse.java
 * 
 * <p>Description: FIDO2 API 查詢會員狀態 Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/5/28, billchang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class QueryUserInfoFido2Response {

    /**
     * 狀態碼
     */
    private String status;

    /**
     * 錯誤訊息
     */
    private String errorMessage;

    /**
     * 錯誤代碼
     */
    private String returnCode;

    /**
     * 伺服器的網域名稱
     */
    private String rpId;

    /**
     * 等於username
     */
    private String memberId;

    /**
     * 0: success, 1: fail
     */
    private String attestationStatus;

    /**
     * 0: disable, 1: enable
     */
    private String memberStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getRpId() {
        return rpId;
    }

    public void setRpId(String rpId) {
        this.rpId = rpId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAttestationStatus() {
        return attestationStatus;
    }

    public void setAttestationStatus(String attestationStatus) {
        this.attestationStatus = attestationStatus;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
}
