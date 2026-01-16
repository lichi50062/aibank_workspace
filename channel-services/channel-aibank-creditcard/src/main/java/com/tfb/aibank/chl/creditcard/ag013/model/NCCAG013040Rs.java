package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RsData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013040Rs.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 040 結果頁-成功</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013040Rs implements RsData {

    /**
     *交易時間
     */
    private String txTime;

    /**
     * 錯誤代碼
     */
    private String errorCode;

    /**
     * 錯誤描述
     */
    private String errorDesc;

    public String getTxTime() {
        return txTime;
    }

    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
