/**
 * 
 */
package com.tfb.aibank.chl.general.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT003010Rq.java
*
* <p>Description: 收付 準備參數去轉帳</p>
*
* <p>Modify History:</p>
* <ol>v1, 2023/06/15, Alex PY Li
*  <li>[新增說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT003011Rq implements RqData {
    /** 選擇最近/常用/約定鍵值 */
    private String selectAccount;

    /**
     * @return the selectAccount
     */
    public String getSelectAccount() {
        return selectAccount;
    }

    /**
     * @param selectAccount
     *            the selectAccount to set
     */
    public void setSelectAccount(String selectAccount) {
        this.selectAccount = selectAccount;
    }
}
