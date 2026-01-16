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
package com.tfb.aibank.component.motplog;

// @formatter:off
/**
 * @(#)MotpLogActionType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/03, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MotpLogActionType {

    READING_PAGE("APPLY", "READING_PAGE"),

    SEND_SMS("APPLY", "SEND_SMS"),

    BINDING_RESULT_PAGE("APPLY", "BINDING_RESULT_PAGE"),

    VERIFY_SIM("APPLY", "VERIFY_SIM"),

    GET_SIM_DATA("APPLY", "GET_SIM_DATA"),

    CREATE_MOTP_ACCOUNT_TASK("APPLY", "CREATE_MOTP_ACCOUNT_TASK"),

    SEND_EMAIL("APPLY", "SEND_EMAIL"),

    DEVICE_ALIVE("CHECK_STATUS", "DEVICE_ALIVE"),

    DEVICE_CHECK_PUSH_MSG_VALID("CHECK_STATUS", "DEVICE_CHECK_PUSH_MSG_VALID"),

    DEVICE_CHECK_KEYPAIR("CHECK_STATUS", "DEVICE_CHECK_KEYPAIR"),

    DEVICE_KEY_LOST("CHECK_STATUS", "DEVICE_KEY_LOST"),

    DEVICE_REMOVED_NONUSE_PROFILE("CHECK_STATUS", "DEVICE_REMOVED_NONUSE_PROFILE"),

    MOTP_REGISTER("DEVICE_SERVICE", "MOTP_REGISTER"),

    BINDING_STATUS("DEVICE_SERVICE", "BINDING_STATUS"),

    GET_NEED_REMOVE_PROFILE("DEVICE_SERVICE", "GET_NEED_REMOVE_PROFILE"),

    GET_BINDING_MB_DEVICE_INFO("DEVICE_SERVICE", "GET_BINDING_MB_DEVICE_INFO"),

    CREATE_MOTP_ACCOUNT("DEVICE_SERVICE", "CREATE_MOTP_ACCOUNT"),

    DISABLE_MOTP_DATA("DEVICE_SERVICE", "DISABLE_MOTP_DATA"),

    CHANGE_REMOTE_UNBIND_DEVICE_STATUS("DEVICE_SERVICE", "CHANGE_REMOTE_UNBIND_DEVICE_STATUS"),

    GET_BINDING_MOTP_DEVICE_INFO("DEVICE_SERVICE", "GET_BINDING_MOTP_DEVICE_INFO"),

    API_REGISTER_TOKEN("MOTP_API", "API_REGISTER_TOKEN"),

    API_VERIFY_OTP("MOTP_API", "API_VERIFY_OTP"),

    API_DELETE_OTP_USER("MOTP_API", "API_DELETE_OTP_USER"),

    API_GET_USER_VALID_TOKENS("MOTP_API", "API_GET_USER_VALID_TOKENS"),

    API_PUSH_MESSAGE("MOTP_API", "API_PUSH_MESSAGE"),

    API_CREATE_OTP_USER("MOTP_API", "API_CREATE_OTP_USER"),

    API_INIT_PUSH("MOTP_API", "API_INIT_PUSH"),

    GENERATE_MOTP("TRANS", "GENERATE_MOTP"),

    VALIDATE_MOTP("TRANS", "VALIDATE_MOTP"),

    IS_MOTP_VALID("TRANS", "IS_MOTP_VALID"),

    TX_SERVICE_IS_MOTP_VALID("TRANS_SERVICE", "TX_SERVICE_IS_MOTP_VALID"),

    TX_SERVICE_VALIDATE_MOTP("TRANS_SERVICE", "TX_SERVICE_VALIDATE_MOTP"),

    TX_SERVICE_GEN_MOTP("TRANS_SERVICE", "TX_SERVICE_GEN_MOTP"),

    DEVICE_UNBIND("UNBIND", "DEVICE_UNBIND"),
    ;

    private String type;

    private String action;

    MotpLogActionType(String type, String action) {
        this.type = type;
        this.action = action;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

}
