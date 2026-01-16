/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)DoFastValidateUserResponse.java 
* 
* <p>Description:SSO/快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class DoFastValidateUserResponse {

    /** 驗證結果 */
    private String statusCode;

    /** TOKEN */
    private String token;

    /** 電話號碼 */
    private String mobileNo;

    /** 平台名稱 */
    private String channelName;
    
    /***/
    private String iddup;
    /***/
    private Integer companyKind;
    
    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * @param mobileNo
     *            the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * @return the channelName
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * @param channelName
     *            the channelName to set
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getIddup() {
        return iddup;
    }

    public void setIddup(String iddup) {
        this.iddup = iddup;
    }

    public Integer getCompanyKind() {
        return companyKind;
    }

    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    @Override
    public String toString() {
        return "DoFastValidateUserResponse [statusCode=" + statusCode + ", token=" + token + ", mobileNo=" + mobileNo + ", channelName=" + channelName + ", iddup=" + iddup + ", companyKind=" + companyKind + "]";
    }

}
