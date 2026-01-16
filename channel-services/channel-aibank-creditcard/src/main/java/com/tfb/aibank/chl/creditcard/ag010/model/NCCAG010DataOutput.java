package com.tfb.aibank.chl.creditcard.ag010.model;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.tfb.aibank.chl.creditcard.resource.dto.ExecuteChangeCcUserPinResponse;

//@formatter:off
/**
* @(#)NCCAG010DataOutput.java
* 
* <p>Description:變更密碼(信用卡) </p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Aaron
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCAG010DataOutput {

    /** email param */
    private Map<String, String> params;

    /** 電文處理結果 */
    private ExecuteChangeCcUserPinResponse response;

    private int errCount = 0;

    /**
     * @return the errCount
     */
    public int getErrCount() {
        return errCount;
    }

    /**
     * @param errCount
     *            the errCount to set
     */
    public void setErrCount(int errCount) {
        this.errCount = errCount;
    }

    /**
     * @return the response
     */
    public ExecuteChangeCcUserPinResponse getResponse() {
        return response;
    }

    /**
     * @param response
     *            the response to set
     */
    public void setResponse(ExecuteChangeCcUserPinResponse response) {
        this.response = response;
    }

    /**
     * @return the params
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     * @param params
     *            the params to set
     */
    public void setParams(Map<String, String> params) {
        this.params = params;
    }

}
