package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW309RRequest.java
* 
* <p>Description:CEW309R 信用卡費自動扣繳設定 上行</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW309RRequest {

    /** 歸戶ID X(19) */
    private String custAcid;
    /** 交易類別 X(1) */
    private String custType;
    /** 原自扣行庫 X(7) */
    private String custOldBnk;
    /** 原自扣帳號 X(14) */
    private String custOldAcc;
    /** 原自扣比率 X(1) */
    private String custOldRtn;
    /** 新自扣行庫 X(7) */
    private String custNewBnk;
    /** 新自扣帳號 X(14) */
    private String custNewAcc;
    /** 新自扣比率 X(1) */
    private String custNewRtn;

    /**
     * @return the custAcid
     */
    public String getCustAcid() {
        return custAcid;
    }

    /**
     * @param custAcid
     *            the custAcid to set
     */
    public void setCustAcid(String custAcid) {
        this.custAcid = custAcid;
    }

    /**
     * @return the custType
     */
    public String getCustType() {
        return custType;
    }

    /**
     * @param custType
     *            the custType to set
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * @return the custOldBnk
     */
    public String getCustOldBnk() {
        return custOldBnk;
    }

    /**
     * @param custOldBnk
     *            the custOldBnk to set
     */
    public void setCustOldBnk(String custOldBnk) {
        this.custOldBnk = custOldBnk;
    }

    /**
     * @return the custOldAcc
     */
    public String getCustOldAcc() {
        return custOldAcc;
    }

    /**
     * @param custOldAcc
     *            the custOldAcc to set
     */
    public void setCustOldAcc(String custOldAcc) {
        this.custOldAcc = custOldAcc;
    }

    /**
     * @return the custOldRtn
     */
    public String getCustOldRtn() {
        return custOldRtn;
    }

    /**
     * @param custOldRtn
     *            the custOldRtn to set
     */
    public void setCustOldRtn(String custOldRtn) {
        this.custOldRtn = custOldRtn;
    }

    /**
     * @return the custNewBnk
     */
    public String getCustNewBnk() {
        return custNewBnk;
    }

    /**
     * @param custNewBnk
     *            the custNewBnk to set
     */
    public void setCustNewBnk(String custNewBnk) {
        this.custNewBnk = custNewBnk;
    }

    /**
     * @return the custNewAcc
     */
    public String getCustNewAcc() {
        return custNewAcc;
    }

    /**
     * @param custNewAcc
     *            the custNewAcc to set
     */
    public void setCustNewAcc(String custNewAcc) {
        this.custNewAcc = custNewAcc;
    }

    /**
     * @return the custNewRtn
     */
    public String getCustNewRtn() {
        return custNewRtn;
    }

    /**
     * @param custNewRtn
     *            the custNewRtn to set
     */
    public void setCustNewRtn(String custNewRtn) {
        this.custNewRtn = custNewRtn;
    }

}
