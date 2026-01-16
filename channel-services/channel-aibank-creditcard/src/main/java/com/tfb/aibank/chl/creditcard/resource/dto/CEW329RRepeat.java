package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW329RRepeat.java
* 
* <p>Description:CEW329R 查詢已綁定之行動支付卡 API下行txRepeat.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW329RRepeat {

    /** 卡片種類 X(4) */
    private String cdsw12;
    /** 實體卡號 X(16) */
    private String hcphyn;
    /** 虛擬卡號 X(16) */
    private String hchcen;
    /** 正附卡別 X(1) */
    private String crdtyp;
    /** 行動支付種類 X(1) */
    private String hcttyp;

    /**
     * @return the cdsw12
     */
    public String getCdsw12() {
        return cdsw12;
    }

    /**
     * @param cdsw12
     *            the cdsw12 to set
     */
    public void setCdsw12(String cdsw12) {
        this.cdsw12 = cdsw12;
    }

    /**
     * @return the hcphyn
     */
    public String getHcphyn() {
        return hcphyn;
    }

    /**
     * @param hcphyn
     *            the hcphyn to set
     */
    public void setHcphyn(String hcphyn) {
        this.hcphyn = hcphyn;
    }

    /**
     * @return the hchcen
     */
    public String getHchcen() {
        return hchcen;
    }

    /**
     * @param hchcen
     *            the hchcen to set
     */
    public void setHchcen(String hchcen) {
        this.hchcen = hchcen;
    }

    /**
     * @return the crdtyp
     */
    public String getCrdtyp() {
        return crdtyp;
    }

    /**
     * @param crdtyp
     *            the crdtyp to set
     */
    public void setCrdtyp(String crdtyp) {
        this.crdtyp = crdtyp;
    }

    /**
     * @return the hcttyp
     */
    public String getHcttyp() {
        return hcttyp;
    }

    /**
     * @param hcttyp
     *            the hcttyp to set
     */
    public void setHcttyp(String hcttyp) {
        this.hcttyp = hcttyp;
    }

}
