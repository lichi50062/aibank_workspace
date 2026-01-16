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
package com.tfb.aibank.chl.system.ot005.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.accountselect.model.FxPayeeRecord;

//@formatter:off
/**
* @(#)NSTOT005020Rs.java
* 
* <p>Description:『最近轉帳/常用/約定』帳號選擇元件 - 用轉出取得對應轉入 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT005050Rs implements RsData {

    /** 轉入帳號 */
    private List<FxPayeeRecord> payees;

    /**
     * @return the payees
     */
    public List<FxPayeeRecord> getPayees() {
        return payees;
    }

    /**
     * @param payees
     *         the payees to set
     */
    public void setPayees(List<FxPayeeRecord> payees) {
        this.payees = payees;
    }

}
