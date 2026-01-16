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
package com.ibm.tw.commons.type;

/**
 * <p>
 * 交易狀態定義
 * </p>
 *
 * @author jeff
 * @version 1.0, Oct 16, 2009
 * @see
 * @since
 */
public enum TxStatusType implements IEnum {

    // =================================
    // 放行完成，尚未執行
    // =================================
    INIT(0, "初始值(建立資料)"),

    INPROCESS(11, "處理中"),

    RESERVED(13, "預約完成"),

    CANCELED(14, "預約註銷"),

    // =================================
    // 交易執行結果
    // =================================
    SUCCESS(20, "交易成功"),

    FAIL(-1, "交易失敗"),

    RESERVED_FAIL(-2, "預約失敗"),

    WAIT_DRAW(27, "等待取款"),

    DRAW_TIME_OUT(28, "取款逾時"),

    /** 未領取 */
    NOT_RECEIVE(21, "未領取"),

    /** 領取失效 */
    INVALID(-3, "領取失效"),

    // =================================
    // 特殊狀態
    // =================================

    LOCK(98, "交易鎖定"),

    UNKNOWN(-99, "狀態不明");

    /** 狀態碼* */
    private int type;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    TxStatusType(int type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    /**
     * 取得狀態碼
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * 取得 memo
     *
     * @return 傳回 memo
     */
    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * 依據狀態碼取得狀態
     *
     * @param type
     * @return
     */
    public static TxStatusType valueOf(int type) {

        for (TxStatusType value : TxStatusType.values()) {
            if (value.getType() == type) {
                return value;
            }
        }
        return TxStatusType.UNKNOWN;
    }

}
