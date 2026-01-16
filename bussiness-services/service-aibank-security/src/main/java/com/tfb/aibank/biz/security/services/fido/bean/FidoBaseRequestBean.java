/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import java.util.Map;

/**
 * @author john
 *
 */
public class FidoBaseRequestBean {

    /** Service API **/
    private String api;

    /** Headers **/
    private Map<String, String> headers;

    /** Params **/
    private Map<String, String> params;

    private String inputParam;

    /**
     * API類型
     * <ul>
     * <li>MIDLogin</li>
     * <li>MIDVerifyResult</li>
     * <li>MIDClause</li>
     * <li>CertLogin</li>
     * <li>CertVerifyResult</li>
     * <li>SignVerifyResult</li>
     * </ul>
     **/
    private String apiType;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam;
    }

    /**
     * 取得apiType
     * 
     * @return apiType
     * @see #apiType
     */
    public String getApiType() {
        return apiType;
    }

    /**
     * 設定 apiType
     * 
     * @param apiType
     * @see #apiType
     */
    public void setApiType(String apiType) {
        this.apiType = apiType;
    }

}
