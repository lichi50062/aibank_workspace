package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCTX001031Rs.java
*
* <p>Description:營業日</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001031Rs implements RsData {

    /** 是否在營業時間內 */
    private boolean isBusinessTime;

    /**
     * @return the isBusinessTime
     */
    public boolean isBusinessTime() {
        return isBusinessTime;
    }

    /**
     * @param isBusinessTime
     *            the isBusinessTime to set
     */
    public void setBusinessTime(boolean isBusinessTime) {
        this.isBusinessTime = isBusinessTime;
    }

}
