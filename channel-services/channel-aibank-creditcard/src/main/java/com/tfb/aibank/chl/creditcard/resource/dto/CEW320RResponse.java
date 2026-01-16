/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW320RResponse.java
* 
* <p>Description: CEW320R 預借現金密碼設定 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW320RResponse {

    /** 處理結果 */
    private String voabnd;

    /**
     * @return the voabnd
     */
    public String getVoabnd() {
        return voabnd;
    }

    /**
     * @param voabnd
     *            the voabnd to set
     */
    public void setVoabnd(String voabnd) {
        this.voabnd = voabnd;
    }

}
