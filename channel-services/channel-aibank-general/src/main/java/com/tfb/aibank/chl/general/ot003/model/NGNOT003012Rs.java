/**
 * 
 */
package com.tfb.aibank.chl.general.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT003010Rs.java
*
* <p>Description: 收付 新增常用帳號</p>
*
* <p>Modify History:</p>
* <ol>v1, 2024/09/05, Yoyo Lin
*  <li>[新增說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT003012Rs implements RsData {

    /** 是否成功 */
    private boolean isSuccess;

    /**
     * @return the isSuccess
     */
    public boolean isSuccess() {
        return isSuccess;
    }

    /**
     * @param isSuccess
     *            the isSuccess to set
     */
    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

}
