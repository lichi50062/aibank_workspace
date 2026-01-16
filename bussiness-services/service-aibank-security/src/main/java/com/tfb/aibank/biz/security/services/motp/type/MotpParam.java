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
package com.tfb.aibank.biz.security.services.motp.type;

import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.ibmb.component.systemparam.ISystemParam;

// @formatter:off
/**
 * @(#)MidParam.java
 * 
 * <p>Description:全景MOTP - API介接參數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/10, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MotpParam implements ISystemParam {

    MOTP_AIBANK_AVALIABLE_FLAG("MOTP系統是否可以使用"),

    MOTP_API_URL_IP("MOTP的IP位置"),

    MOTP_API_ACCT("MOTP的API帳號"),

    MOTP_API_PWD("MOTP的API密碼"),

    MOTP_API_GET_USER_VALID_TOKENS("取得使用者載具序號"),

    MOTP_API_CREATE_OTP_USER("新增OTP使用者"),

    MOTP_API_INIT_PUSH("取得初始化推播資訊"),

    MOTP_API_REGISTER_TOKEN("註冊載具"),

    MOTP_API_VIEW_USERS_TOKENS("取得使用者載具資訊"),

    MOTP_API_DELETE_OTP_USER("刪除OTP使用者"),

    MOTP_API_PUSH_MESSAGE("推播訊息"),

    MOTP_API_VERIFY_OTP("驗證OTP"),

    MOTP_AUTH_EXPIRE_TIMES("驗證截止時效(單位:秒)");

    /** 狀態說明 */
    private String memo;

    MotpParam(String memo) {
        this.memo = memo;
    }

    @Override
    public IBSystemId getCategory() {
        return IBSystemId.PIB;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public int getPinFlag() {
        return 0;
    }

}
