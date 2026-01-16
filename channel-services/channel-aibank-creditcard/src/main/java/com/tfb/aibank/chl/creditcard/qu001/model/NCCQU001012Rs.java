package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;

//@formatter:off
/**
* @(#)NCCQU001012Rs.java
* 
* <p>Description:信用卡總覽 功能首頁 取得本期未出帳消費明細 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001012Rs implements RsData {
    /** 信用卡帳務資料發生錯誤 */
    private boolean cew303rError;
    /** 信用卡帳務資料發生錯誤-錯誤代碼 */
    private String cew303rErrorCode;
    /** 帳務資料 */
    private CEW303RResponse cew303r;

    /**
     * @return the cew303rError
     */
    public boolean isCew303rError() {
        return cew303rError;
    }

    /**
     * @param cew303rError
     *            the cew303rError to set
     */
    public void setCew303rError(boolean cew303rError) {
        this.cew303rError = cew303rError;
    }

    /**
     * @return the cew303r
     */
    public CEW303RResponse getCew303r() {
        return cew303r;
    }

    /**
     * @param cew303r
     *            the cew303r to set
     */
    public void setCew303r(CEW303RResponse cew303r) {
        this.cew303r = cew303r;
    }

    public String getCew303rErrorCode() {
        return cew303rErrorCode;
    }

    public void setCew303rErrorCode(String cew303rErrorCode) {
        this.cew303rErrorCode = cew303rErrorCode;
    }
}
