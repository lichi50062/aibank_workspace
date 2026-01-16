/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.datacenter.model;

//@formatter:off
/**
* @(#)UserTagRs.java
* 
* <p>Description:數據中心基本欄位 - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
* 
* 
{
  "message": "OK",
  "result": {
    "userTags": {
      "T01-01": "lazy",
      "T01-02": "cons"
    },
    "timestamp": 1685515741900
  }
}
*/
//@formatter:on
public class UserTagRs implements DataCenterBaseRs {
    /** 數據中台端自定義之訊息 */
    private String message;
    /** 若Http status code為"200"，將回傳API Response; 若不為"200"，將回傳空物件{} */
    private UserTagResult result;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the result
     */
    public UserTagResult getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(UserTagResult result) {
        this.result = result;
    }

}
