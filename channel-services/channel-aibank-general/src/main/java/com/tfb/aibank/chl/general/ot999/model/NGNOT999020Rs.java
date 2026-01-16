package com.tfb.aibank.chl.general.ot999.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT999020Rs.java 
* 
* <p>Description:快速身份認證</p>
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
@Component
public class NGNOT999020Rs extends NGNOT999Rs implements RsData {

    /**
     * 行銀快速身份認證 狀態碼
     */
    private String statusCode;
//
//    /**
//     * 進行OTP認證
//     */
//    private boolean isOtpRequired;
    
    /**
     * 失敗後是否需執行下一步認證
     */
    private boolean isNextFallBackAuthRequired;
    /**
     * 是否需進行下一步需執檢證
     */
    private boolean isNextAdditionalAuthRequired;
    
    /**
     * 下一步認證的種類 
     */
    private String nextAuthType = "0";
    
    /**
     * 行銀快速身份認證 CallBack Url
     */
    private String callBackUrl;
    
    

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
     * @return the callBackUrl
     */
    public String getCallBackUrl() {
        return callBackUrl;
    }

    /**
     * @param callBackUrl
     *            the callBackUrl to set
     */
    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }
    /**
     * 
     * @return
     */
    public boolean isNextFallBackAuthRequired() {
        return isNextFallBackAuthRequired;
    }
    /**
     * 
     * @param isNextFallBackAuthRequired
     */
    public void setNextFallBackAuthRequired(boolean isNextFallBackAuthRequired) {
        this.isNextFallBackAuthRequired = isNextFallBackAuthRequired;
    }
    /**
     * 
     * @return
     */
    public boolean isNextAdditionalAuthRequired() {
        return isNextAdditionalAuthRequired;
    }
    /**
     * 
     * @param isNextAdditionalAuthRequired
     */
    public void setNextAdditionalAuthRequired(boolean isNextAdditionalAuthRequired) {
        this.isNextAdditionalAuthRequired = isNextAdditionalAuthRequired;
    }
    /**
     * 
     * @return
     */
    public String getNextAuthType() {
        return nextAuthType;
    }
    /**
     * 
     * @param nextAuthType
     */
    public void setNextAuthType(String nextAuthType) {
        this.nextAuthType = nextAuthType;
    }

}
