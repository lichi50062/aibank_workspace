package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001040Rs.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 costco refresh RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/31, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001042Rs implements RsData {

    /** costco錯誤 */
    private boolean costcoError;

    /** 好多金 正負值 X(1) */
    private String signFlg3;
    /** 好多金 本期增加 9(9) */
    private BigDecimal addamt;
    /**
     * @return the costcoError
     */
    public boolean isCostcoError() {
        return costcoError;
    }
    
    /**
     * @param costcoError
     *            the costcoError to set
     */
    public void setCostcoError(boolean costcoError) {
        this.costcoError = costcoError;
    }
    /**
     * @return the signFlg3
     */
    public String getSignFlg3() {
        return signFlg3;
    }
    
    /**
     * @param signFlg3
     *            the signFlg3 to set
     */
    public void setSignFlg3(String signFlg3) {
        this.signFlg3 = signFlg3;
    }
    /**
     * @return the addamt
     */
    public BigDecimal getAddamt() {
        return addamt;
    }
    
    /**
     * @param addamt
     *            the addamt to set
     */
    public void setAddamt(BigDecimal addamt) {
        this.addamt = addamt;
    }

}
