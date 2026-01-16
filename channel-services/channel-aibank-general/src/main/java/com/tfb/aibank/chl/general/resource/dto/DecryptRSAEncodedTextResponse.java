/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)DecryptRSAEncodedTextResponse.java 
* 
* <p>Description:E2EE 解密文字</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class DecryptRSAEncodedTextResponse {

    /** 原始資料 */
    private String rawText;

    /**
     * @return the rawText
     */
    public String getRawText() {
        return rawText;
    }

    /**
     * @param rawText
     *            the rawText to set
     */
    public void setRawText(String rawText) {
        this.rawText = rawText;
    }

}
