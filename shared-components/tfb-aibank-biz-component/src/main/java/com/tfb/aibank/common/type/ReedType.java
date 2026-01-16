package com.tfb.aibank.common.type;

import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)ReedType.java
* 
* <p>Description:[限價方式]</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/16, leiley 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum ReedType {

    /** 參考贖回報價減1%(含)以內 */
    SUBTRACTION_1("1", "4", "參考贖回報價減1%(含)以內"),

    /** 參考贖回報價減3%(含)以內 */
    SUBTRACTION_3("2", "5", "參考贖回報價減3%(含)以內"),

    /** 參考贖回報價減5%(含)以內 */
    SUBTRACTION_5("3", "6", "參考贖回報價減5%(含)以內"),

    /** 發行機構或券商回覆之實際成交價格 */
    MARKET_PRICE("4", "2", "發行機構或券商回覆之實際成交價格"),

    /** 當日限價 */
    LINIT_PRICE("4", "1", "當日限價"),

    /** 自行輸入 */
    MANUAL_INPUT("5", "2", "自行輸入"),

    UNKNOWN("-1", "-1", "UNKNOWN");

    private String code;
    private String msgCode;
    private String memo;

    private ReedType(String code, String msgCode, String memo) {
        this.code = code;
        this.msgCode = msgCode;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msgCode
     */
    public String getMsgCode() {
        return msgCode;
    }

    /**
     * @param msgCode
     *            the msgCode to set
     */
    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static ReedType findByCode(String type) {
        for (ReedType enumType : ReedType.values()) {
            if (StringUtils.equals(type, enumType.getCode())) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    public static ReedType findByMsgCode(String type) {
        for (ReedType enumType : ReedType.values()) {
            if (StringUtils.equals(type, enumType.getMsgCode())) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    /** 參考贖回報價減1%(含)以內 */
    public boolean isSubtraction1() {
        return equals(ReedType.SUBTRACTION_1);
    }

    /** 參考贖回報價減3%(含)以內 */
    public boolean isSubtraction3() {
        return equals(ReedType.SUBTRACTION_3);
    }

    /** 參考贖回報價減5%(含)以內 */
    public boolean isSubtraction5() {
        return equals(ReedType.SUBTRACTION_5);
    }

    /** 若為發行機構或券商回覆之實際成交價格(市價 */
    public boolean isMarketPrice() {
        return equals(ReedType.MARKET_PRICE);
    }

    /** 自行輸入 */
    public boolean isManualInput() {
        return equals(ReedType.MANUAL_INPUT);
    }

    public boolean isUnknown() {
        return equals(ReedType.UNKNOWN);
    }
}
