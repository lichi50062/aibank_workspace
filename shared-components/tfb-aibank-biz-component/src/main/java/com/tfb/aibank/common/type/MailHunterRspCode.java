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

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)MailHunterRspCode.java
 * 
 * <p>Description:Mail Hunter 回覆碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum MailHunterRspCode implements IEnum {

    RSP_CODE_01("1", "呼叫成功"),

    RSP_CODE_02("2", "呼叫失敗，郵件內容有機敏資料的疑慮"),

    RSP_CODE_03("3", "必填欄位不全"),

    RSP_CODE_04("4", "有附件檔，但未傳入䒧應的附件檔檔名"),

    RSP_CODE_05("5", "只允許上傳副檔名為{att_filename}的檔案"),

    RSP_CODE_06("6", "總夾檔檔案大小限制在{att_filesize}M內"),

    RSP_CODE_07("7", "電子郵件為黑名單"),

    RSP_CODE_08("8", "此IP不允許使用"),

    RSP_CODE_09("9", "專案類別預設值不存在"),

    RSP_CODE_10("10", "其他錯誤，例如DB連線問題"),

    UNKNOWN(UNKNOWN_STR_CODE, "未知");

    /** 狀態碼 */
    private String code;

    /** 狀態說明 */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    MailHunterRspCode(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    /**
     * 取得 memo
     *
     * @return 傳回 memo。
     */
    @Override
    public String getMemo() {
        return memo;
    }

    public static MailHunterRspCode find(String code) {
        for (MailHunterRspCode group : MailHunterRspCode.values()) {
            if (StringUtils.equals(group.getCode(), code)) {
                return group;
            }
        }
        return RSP_CODE_01;
    }

    public boolean isSuccess() {
        return equals(RSP_CODE_01);
    }

}