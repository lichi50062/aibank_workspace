package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001021Rs.java
* 
* <p>Description:信用卡總覽 卡片管理頁 重新載入年度累積消費資料 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Lis
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001021Rs implements RsData {

    /** 發送電文CEW321R取得年度累積消費資料失敗 */
    private boolean cew321rError;
    /** 累計起算年月 X(5) */
    private Date strday;
    /** 累計迄期年月 X(5) */
    private Date endday;
    /** 免年費消費金額標準 X(7) */
    private BigDecimal outamt1;
    /** 免年費消費次數標準 X(3) */
    private String outcnt1;
    /** 累積年消費金額 X(9) */
    private String outamt2;
    /** 累積年消費次數 X(7) */
    private String outcnt2;
    /**
     * @return the cew321rError
     */
    public boolean isCew321rError() {
        return cew321rError;
    }
    
    /**
     * @param cew321rError
     *            the cew321rError to set
     */
    public void setCew321rError(boolean cew321rError) {
        this.cew321rError = cew321rError;
    }
    
    /**
     * @return the strday
     */
    public Date getStrday() {
        return strday;
    }

    /**
     * @param strday
     *            the strday to set
     */
    public void setStrday(Date strday) {
        this.strday = strday;
    }

    /**
     * @return the endday
     */
    public Date getEndday() {
        return endday;
    }

    /**
     * @param endday
     *            the endday to set
     */
    public void setEndday(Date endday) {
        this.endday = endday;
    }

    /**
     * @return the outamt1
     */
    public BigDecimal getOutamt1() {
        return outamt1;
    }

    /**
     * @param outamt1
     *            the outamt1 to set
     */
    public void setOutamt1(BigDecimal outamt1) {
        this.outamt1 = outamt1;
    }

    /**
     * @return the outcnt1
     */
    public String getOutcnt1() {
        return outcnt1;
    }

    /**
     * @param outcnt1
     *            the outcnt1 to set
     */
    public void setOutcnt1(String outcnt1) {
        this.outcnt1 = outcnt1;
    }

    /**
     * @return the outamt2
     */
    public String getOutamt2() {
        return outamt2;
    }

    /**
     * @param outamt2
     *            the outamt2 to set
     */
    public void setOutamt2(String outamt2) {
        this.outamt2 = outamt2;
    }

    /**
     * @return the outcnt2
     */
    public String getOutcnt2() {
        return outcnt2;
    }
    
    /**
     * @param outcnt2
     *            the outcnt2 to set
     */
    public void setOutcnt2(String outcnt2) {
        this.outcnt2 = outcnt2;
    }

}
