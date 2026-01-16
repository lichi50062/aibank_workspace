/**
 * 
 */
package com.tfb.aibank.chl.creditcard.tx001.model;

//@formatter:off
/**
* @(#)NCCTX001AccountInfo.java
*
* <p>Description:預借現金 帳戶清單</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCTX001AccountInfo {

    /** 帳號識別鍵值 */
    private String accountKey;

    /** 帳號 */
    private String acno;

    /**
     * @return the accountKey
     */
    public String getAccountKey() {
        return accountKey;
    }

    /**
     * @param accountKey
     *            the accountKey to set
     */
    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

}
