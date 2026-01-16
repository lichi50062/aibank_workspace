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
public enum RedeemTxType {
  //@formatter:off

    /** 當日單贖回*/
    REALTIME ("3", "當日單"),
    
    /** 預約單贖回*/
    RESERVED("11", "預約單"),
    
    /** 長效單贖回 good-till-canceled*/
    GTC("13", "長效單"),
    
    UNKNOWN("-1", "UNKNOWN");
    
  //@formatter:on

    private String code;
    private String memo;

    private RedeemTxType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static RedeemTxType find(String code) {
        for (RedeemTxType type : RedeemTxType.values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }
        return RedeemTxType.UNKNOWN;
    }

    /** 單日單 */
    public boolean isRealtime() {
        return equals(RedeemTxType.REALTIME);
    }

    /** 預約單 */
    public boolean isReserved() {
        return equals(RedeemTxType.RESERVED);
    }

    /** 長效單 */
    public boolean isGtc() {
        return equals(RedeemTxType.GTC);
    }

    public boolean isUnknown() {
        return equals(RedeemTxType.UNKNOWN);
    }
}
