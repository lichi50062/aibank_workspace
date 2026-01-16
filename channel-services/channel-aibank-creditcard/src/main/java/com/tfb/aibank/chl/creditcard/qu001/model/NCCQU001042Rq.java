package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCQU001040Rq.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 costco refresh RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/31, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001042Rq implements RqData {
    private String monthType;

    /**
     * @return the monthType
     */
    public String getMonthType() {
        return monthType;
    }

    /**
     * @param monthType
     *            the monthType to set
     */
    public void setMonthType(String monthType) {
        this.monthType = monthType;
    }
}
