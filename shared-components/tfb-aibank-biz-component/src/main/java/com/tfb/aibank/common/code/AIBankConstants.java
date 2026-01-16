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
package com.tfb.aibank.common.code;

import java.math.BigDecimal;

// @formatter:off
/**
 * @(#)AIBankConstants.java
 * 
 * <p>Description:TFB AI Bank 用的常數</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class AIBankConstants {

    /** 通路名稱 */
    public static final String CHANNEL_NAME = "AIBANK";

    /** TFB_BANK_CODE */
    public static final String TFB_BANK_CODE = "012";
    /** 日盛銀行代碼 */
    public static final String BANK_ID_815 = "815";

    /** 交易時間類型 1 */
    public static final String TRANSTIME_TYPE = "1";

    /** 預設 SecretKeyProvider */
    public static final String CRYPTO_SECRET_KEY_PROVIDER_DEFAULT = "com.tfb.aibank.component.crypto.DBSecretKeyProvider";

    /** 預設分行代碼 */
    public static final String PIB_DEFAULT_BRANCH_ID = "200";
    /** 台北富邦網路銀行 */
    public static final String PIB_BANK_EMAIL = "ebank@fubon.com";
    /** 金額無上限 */
    public static final BigDecimal UNLIMITED_QUOTA = new BigDecimal(-1);
    /** B2C授權中心系統代碼 */
    public static final String B2C_UAA_APP_ID = "CAA";
    /** B2C登入後首頁 */
    public static final String B2C_LOGIN_FIRST_TASKID = "CGEQU001";
    /** B2C授權中心系統代碼 */
    public static final String B2E_UAA_APP_ID = "EAA";
    /** B2E登入後首頁 */
    public static final String B2E_LOGIN_FIRST_TASKID = "EHMQU001";
    /** 扣繳項目代碼 - 水費 */
    public static final String[] APPLY_BILL_WATER_FEE = { "3777", "4905" };
    /** 扣繳項目代碼 - 電費 */
    public static final String APPLY_BILL_ELECTRICITY_FEE = "6068";
    /** 扣繳項目代碼 - 中華電信電信費 */
    public static final String[] APPLY_BILL_CHUNGHWA_FEE = { "6071", "0168", "2119" };

    /** 狀態碼 W600:更新帳務日 */
    public static final String STATUS_CODE_ACCOUNT_DAY = "W600";

    /** 預設頭像角色 */
    public static final String AVATAR_CHARACTER_DEFAULT = "avatar1";

    public static final String MAIN_ACCOUNT_CUR_CODE = "XXX";

    /** Table 「DIC」Category => BUYFUND  */
    public static final String DIC_CATE_BUYFUND = "BUYFUND";
    /** 交易相關Table中，AiBank的 TX_SOURCE */
    public static final String TX_SOURCE_AIBANK = "3";

}
