/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

//@formatter:off
/**
* @(#)GetFastLoginUserResponse.java 
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
public class GetFastValidateUserResponse {

    /** statusCode */
    private String statusCode;
    
    /** 認證方式 */
    private String authType;
    
    /** 綁定使用者 */
    private String custId;
    
    /** 目標平台鍵值 */
    private String channelKey;
    
    /** 開啟方式 */
    private String openType;
    
    /** Header顯示方式 */
    private String moduleType;
    
    /**
     * 內崁參數
     */
    private String moduleParam;
    
    /**
     * Func
     */
    private String func;

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the authType
     */
    public String getAuthType() {
        return authType;
    }

    /**
     * @param authType the authType to set
     */
    public void setAuthType(String authType) {
        this.authType = authType;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the channelKey
     */
    public String getChannelKey() {
        return channelKey;
    }

    /**
     * @param channelKey the channelKey to set
     */
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    /**
     * @return the openType
     */
    public String getOpenType() {
        return openType;
    }

    /**
     * @param openType the openType to set
     */
    public void setOpenType(String openType) {
        this.openType = openType;
    }

    /**
     * @return the moduleType
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * @param moduleType the moduleType to set
     */
    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    /**
     * @return the moduleParam
     */
    public String getModuleParam() {
        return moduleParam;
    }

    /**
     * @param moduleParam the moduleParam to set
     */
    public void setModuleParam(String moduleParam) {
        this.moduleParam = moduleParam;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    @Override
    public String toString() {
        return "GetFastValidateUserResponse [statusCode=" + statusCode + ", authType=" + authType + ", custId=" + custId + ", channelKey=" + channelKey + ", openType=" + openType + ", moduleType=" + moduleType + ", moduleParam=" + moduleParam + ", func=" + func + "]";
    }
    
}
