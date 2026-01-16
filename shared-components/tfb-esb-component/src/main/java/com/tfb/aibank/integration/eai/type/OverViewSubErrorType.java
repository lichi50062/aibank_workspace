package com.tfb.aibank.integration.eai.type;
// @formatter:off
/**
 * @(#)OverViewSubErrorType.java
 *
 * <p>Description:[程式說明]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, MartyPan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum OverViewSubErrorType {
    BPM("00", "Middleware"),
    EB555692("10", "存款"),
    EB555789("11", "存款"),
    VN084N("20", "投資-基金"),
    //UM085N("22", "投資-指單"),
    NJW084("23", "投資-債券"),
    UK084N("24", "投資-海外股票"),
    SPWEBQ1("25", "投資-組合式商品(AS400)"),
    VN084N1("26", "投資-OBU基金"),
    AJW084("27", "投資-OBU債券"),
    BKDCD002("28", "投資-組合式商品(DCD)"),
    EB120140("29", "投資-黃金存摺內容明細查詢"),
    InsureASTable("30", "保險總覽資料-主從表格名稱設定"),
    InsureTotal("31", "保險總覽資料"),
    InsureDetail("32", "保險明細資料"),
    NMP8YB("33", "奈米投契約查詢"),
    StockASTable("40", "證券總覽資料-主從表格名稱設定"),
    StockTotal("41", "證券總覽資料"),
    StockUserProfile("42", "網銀-申請網銀查詢證券資料"),
    CEW313R("50", "信用卡-帳單金額"),
    UNKNOWN_ERROR("99", "未知的錯誤");
    private String code;
    private String desc;

    private OverViewSubErrorType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 取得 code
     * 
     * @return 傳回 code。
     */
    public String getCode() {
        return code;
    }

    /**
     * 取得 desc
     * 
     * @return 傳回 desc。
     */
    public String getDesc() {
        return desc;
    }
}
