/**
 * 
 */
package com.tfb.aibank.chl.creditcard.tx001.model;

//@formatter:off
/**
* @(#)NCCTX001BranchInfo.java
*
* <p>Description:預借現金 分行清單</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCTX001BranchInfo {

    /** 分行代碼 */
    private String branchId;

    /** 分行名稱 */
    private String branchName;

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId
     *            the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName
     *            the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

}
