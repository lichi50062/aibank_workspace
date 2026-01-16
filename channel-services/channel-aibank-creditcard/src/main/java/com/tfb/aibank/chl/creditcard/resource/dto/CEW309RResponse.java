package com.tfb.aibank.chl.creditcard.resource.dto;

//@formatter:off
/**
* @(#)CEW309RResponse.java
* 
* <p>Description:CEW309R 信用卡費自動扣繳設定 API下行欄位.</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW309RResponse {

    /** 異動結果 X(4) */
    private String abndCode;
    /** 目前自扣行庫 X(7) */
    private String custCurBnk;
    /** 目前自扣帳號 X(14) */
    private String custCurAcc;
    /** 目前自扣比率 X(1) */
    private String custCurRtn;
    /** 最近申請自扣行庫 X(7) */
    private String custIbkBnk;
    /** 最近申請自扣帳號 X(14) */
    private String custIbkAcc;
    /** 最近申請自扣比率 X(1) */
    private String custIbkRtn;
    /** 最近申請自扣狀態 X(1) */
    private String cusIbkSts;

    /**
     * @return the abndCode
     */
    public String getAbndCode() {
        return abndCode;
    }

    /**
     * @param abndCode
     *            the abndCode to set
     */
    public void setAbndCode(String abndCode) {
        this.abndCode = abndCode;
    }

    /**
     * @return the custCurBnk
     */
    public String getCustCurBnk() {
        return custCurBnk;
    }

    /**
     * @param custCurBnk
     *            the custCurBnk to set
     */
    public void setCustCurBnk(String custCurBnk) {
        this.custCurBnk = custCurBnk;
    }

    /**
     * @return the custCurAcc
     */
    public String getCustCurAcc() {
        return custCurAcc;
    }

    /**
     * @param custCurAcc
     *            the custCurAcc to set
     */
    public void setCustCurAcc(String custCurAcc) {
        this.custCurAcc = custCurAcc;
    }

    /**
     * @return the custCurRtn
     */
    public String getCustCurRtn() {
        return custCurRtn;
    }

    /**
     * @param custCurRtn
     *            the custCurRtn to set
     */
    public void setCustCurRtn(String custCurRtn) {
        this.custCurRtn = custCurRtn;
    }

    /**
     * @return the custIbkBnk
     */
    public String getCustIbkBnk() {
        return custIbkBnk;
    }

    /**
     * @param custIbkBnk
     *            the custIbkBnk to set
     */
    public void setCustIbkBnk(String custIbkBnk) {
        this.custIbkBnk = custIbkBnk;
    }

    /**
     * @return the custIbkAcc
     */
    public String getCustIbkAcc() {
        return custIbkAcc;
    }

    /**
     * @param custIbkAcc
     *            the custIbkAcc to set
     */
    public void setCustIbkAcc(String custIbkAcc) {
        this.custIbkAcc = custIbkAcc;
    }

    /**
     * @return the custIbkRtn
     */
    public String getCustIbkRtn() {
        return custIbkRtn;
    }

    /**
     * @param custIbkRtn
     *            the custIbkRtn to set
     */
    public void setCustIbkRtn(String custIbkRtn) {
        this.custIbkRtn = custIbkRtn;
    }

    /**
     * @return the cusIbkSts
     */
    public String getCusIbkSts() {
        return cusIbkSts;
    }

    /**
     * @param cusIbkSts
     *            the cusIbkSts to set
     */
    public void setCusIbkSts(String cusIbkSts) {
        this.cusIbkSts = cusIbkSts;
    }

}
