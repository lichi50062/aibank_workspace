package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013040Rq.java
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
public class NCCAG013040Rq implements RqData {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
