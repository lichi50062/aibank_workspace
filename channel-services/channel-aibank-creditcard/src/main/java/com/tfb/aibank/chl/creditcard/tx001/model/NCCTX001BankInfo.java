/**
 * 
 */
package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

//@formatter:off
/**
* @(#)NCCTX001BankInfo.java
*
* <p>Description:預借現金 銀行清單</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001BankInfo {

    /**
     * 銀行代碼
     */
    private String bankId;

    /**
     * 銀行名稱
     */
    private String bankName;

    /**
     * @return the bankId
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId
     *            the bankId to set
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName
     *            the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
