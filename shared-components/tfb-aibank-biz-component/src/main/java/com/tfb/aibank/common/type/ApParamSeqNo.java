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
 * @(#)ApParamSeqNo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum ApParamSeqNo {
    CARRY_OUT_UP_BOUND("0081", "放行筆數限制之_筆數"),

    TX_DETAIL_PAGE_AMOUNT("0083", "交易明細查詢每頁筆數"),

    TIMEOUT_MINUTES("1016", "前台系統逾時登出timeout時間(分)"),

    LOGOUT_COUNTDOWN_SECONDS("1014", "前台系統逾時倒數登出timeout時間(秒)"),

    FXEX_TRANSFER_FORBIDDEN_ID_TYPE("FA01", "外幣單筆轉帳功能禁止身分別"),

    FXEX_TRANSFER_01_FORBIDDEN_ID_TYPE("FR01", "外幣單筆匯款功能禁止身分別"),

    FXEX_TRANSFER_START_TIME("FR01", "國外匯款可申請交易起始時間"),

    FXEX_TRANSFER_END_TIME("FR02", "國外匯款可申請交易結束時間"),

    FXEX_TRANSFER_02_FORBIDDEN_ID_TYPE("FR07", "外幣單筆匯款功能禁止身分別"),

    MOTP_FLAG("0100", "MOTP 是否開啟 （Y/N）"),

    TX_DETAIL_BLOCK_COUNT("0101", "詳細資料預設可顯示筆數"),

    MARKETING_TIMEOUT("1017", "數位軌跡 請求逾時");

    private String seqNo;
    private String memo;

    private ApParamSeqNo(String seqNo, String memo) {
        this.seqNo = seqNo;
        this.memo = memo;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}
