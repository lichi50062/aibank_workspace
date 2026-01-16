/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)DecryptRSAEncodedTextRequest.java 
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
public class DecryptRSAEncodedTextRequest {

    private String encodedText;

    // 是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]
    private Boolean isPwdWithTime;

    /**
     * @return the encodedText
     */
    public String getEncodedText() {
        return encodedText;
    }

    /**
     * @param encodedText
     *            the encodedText to set
     */
    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

    /**
     * @return the isPwdWithTime
     */
    public Boolean getIsPwdWithTime() {
        return isPwdWithTime;
    }

    /**
     * @param isPwdWithTime
     *            the isPwdWithTime to set
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
