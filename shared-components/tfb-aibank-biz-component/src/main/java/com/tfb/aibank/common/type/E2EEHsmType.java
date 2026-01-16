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
package com.tfb.aibank.common.type;

// @formatter:off
/**
 * @(#)E2EEHsmType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, John
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum E2EEHsmType {

    /**
     * 舊有一般用戶登入,主機電文 return EB5556981.PA_SS_WOR_RD@EB5556981.ACNOID
     */
    PWD_EB5556981_CP1047("CP1047"),
    /**
     * 一般會員登入驗證密碼
     * 會回傳增強後的密碼@SHA512(原密碼密文+idno十碼)@SHA512(acno原密碼密文+idno十碼)
     */
    PWD_EB5556986_CP1047_PWD_ENHANCE("CP1047", PWD_EB5556981_CP1047, true),
    /**
     * 舊有一般用戶登入,主機電文EB5556981.PA_SS_WOR_RD/EB5556981.NEWPA_SS_WOR_RD
     */
    PWD_MPVV_CP1047("CP1047"),
    /**
     * 一般用戶登入密碼or修改使用者代號/密碼,主機電文EB5556981.ACNOID or EB5556981.REMARK
     */
    PWD_3DES_CP1047("CP1047"),
    /**
     * WebATM重設帳號密碼 EB552170.PA_SS_WOR_RD
     */
    PWD_MPVV_CP937("CP937"),
    /**
     * FINI(CEH)/CES/CST登入
     */
    DB_MD5_UTF8("UTF8"),
    /**
     * 信用卡會員登入/修改密碼 EXT/B2C/BFS/MBANK
     */
    DB_3DES_UTF8("UTF8"),
    /**
     * 手勢登入手勢密碼加密與DB驗證
     */
    ENCRYPT_3DES_UTF8("UTF8"),
    /**
     * 信用卡會員登入/修改密碼 EXT/B2C/BFS/MBANK 會回傳增強後的密碼@SHA512
     */
    DB_3DES_UTF8_PWD_ENHANCE("UTF8", DB_3DES_UTF8, true),
    /**
     * 生物辨識公鑰解密
     */
    DECRYPT_3DES_UTF8("UTF8"),

    UNKNOWN("UNKNOWN");

    private String encoding;

    private E2EEHsmType parentHSMType;

    private boolean isNeedEnhancePwd = false;

    E2EEHsmType() {

    }

    E2EEHsmType(String encoding) {
        this.encoding = encoding;
    }

    E2EEHsmType(String encoding, E2EEHsmType parentHSMType, Boolean isNeedEnhancePwd) {
        this.encoding = encoding;
        this.parentHSMType = parentHSMType;
        this.isNeedEnhancePwd = isNeedEnhancePwd;
    }

    public static E2EEHsmType find(String encoding) {
        for (E2EEHsmType encode : E2EEHsmType.values()) {

            if (encode.getEncoding().equals(encoding)) {
                return encode;
            }
        }

        return E2EEHsmType.UNKNOWN;

    }

    public static E2EEHsmType findbyName(String name) {
        for (E2EEHsmType e2eeHsmType : E2EEHsmType.values()) {

            if (e2eeHsmType.name().equals(name)) {
                return e2eeHsmType;
            }
        }
        return null;
    }

    public String getEncoding() {
        return encoding;
    }

    /**
     * @return the parentHSMType
     */
    public E2EEHsmType getParentHSMType() {
        return parentHSMType == null ? this : parentHSMType;
    }

    /**
     * @param parentHSMType
     *            the parentHSMType to set
     */
    public void setParentHSMType(E2EEHsmType parentHSMType) {
        this.parentHSMType = parentHSMType;
    }

    /**
     * @return the isNeedEnhancePwd
     */
    public boolean isNeedEnhancePwd() {
        return isNeedEnhancePwd;
    }

    /**
     * @param isNeedEnhancePwd
     *            the isNeedEnhancePwd to set
     */
    public void setNeedEnhancePwd(boolean isNeedEnhancePwd) {
        this.isNeedEnhancePwd = isNeedEnhancePwd;
    }

    /**
     * @param encoding
     *            the encoding to set
     */
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

}
