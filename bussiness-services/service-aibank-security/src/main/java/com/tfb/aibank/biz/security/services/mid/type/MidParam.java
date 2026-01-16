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
package com.tfb.aibank.biz.security.services.mid.type;

import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.ibmb.component.systemparam.ISystemParam;

// @formatter:off
/**
 * @(#)MidParam.java
 * 
 * <p>Description:台網MID驗證 - API介接參數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/05, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MidParam implements ISystemParam {

    MOTP_MID_IP("MOTP TWID Portal的IP位置"),

    MOTP_MID_LOGIN_PATH("MOTP TWID Portal的取得token路徑"),

    MOTP_MID_VERIFY_RESULT_PATH("MOTP TWID Portal的queryVerifyResult路徑"),

    MOTP_MID_BUSSINESS_NO("MOTP TWID 應用平台代號[由TWID Portal產生後提供]"),

    MOTP_MID_API_VERSION("MOTP TWID API 版本 [固定值1.0]"),

    MOTP_MID_HASH_KEY_NO("MOTP TWID HashKey 組別 [預設1，之後換key再變更]"),

    MOTP_MID_HASH_KEY("MOTP TWID HashKey 組別 [預設TWCADefault，之後換key再變更]"),

    MOTP_MID_CLAUSE_VER("MOTP TWID SIM認證之閱讀條款版本");

    /** 狀態說明 */
    private String memo;

    MidParam(String memo) {
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
