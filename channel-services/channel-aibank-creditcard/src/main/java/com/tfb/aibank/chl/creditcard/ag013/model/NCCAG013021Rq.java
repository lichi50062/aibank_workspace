package com.tfb.aibank.chl.creditcard.ag013.model;

import com.ibm.tw.ibmb.base.model.RqData;
import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCAG013020Rq.java
 * 
 * <p>Description:信用卡/簽帳金融卡FIDO2綁定 020 同意條款</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/23, Evans
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG013021Rq implements RqData {

    private String errorDesc;

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }
}
