package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001021Rs.java
* 
* <p>Description:信用卡總覽 卡片管理頁 取得保費權益資訊 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Lis
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001022Rs implements RsData {

    /** 保費權益種類 */
    private String insuType;

    /** 發送電文CEW327R取得保費權益資訊失敗 */
    private boolean cew327rError;

    /**
     * @return the insuType
     */
    public String getInsuType() {
        return insuType;
    }

    /**
     * @param insuType
     *            the insuType to set
     */
    public void setInsuType(String insuType) {
        this.insuType = insuType;
    }

    /**
     * @return the cew327rError
     */
    public boolean isCew327rError() {
        return cew327rError;
    }

    /**
     * @param cew327rError
     *            the cew327rError to set
     */
    public void setCew327rError(boolean cew327rError) {
        this.cew327rError = cew327rError;
    }

}
