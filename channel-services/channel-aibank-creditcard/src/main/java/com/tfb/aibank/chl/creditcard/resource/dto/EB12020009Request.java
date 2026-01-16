/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Calendar;

//@formatter:off
/**
* @(#)EB12020009Request.java
* 
* <p>Description:EB12020009 預借現金 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB12020009Request {

    /** 功能 */
    private String funcCod;

    /** 交易流水號 */
    private String seqNo;

    /** 功能別 */
    private String func;

    /** 統編 */
    private String idno;

    /** 卡號 */
    private String vncdno;

    /** 效期 */
    private String vendym;

    /** 卡片背面末三碼 */
    private String vncvv2;

    /** 交易金額 */
    private String vntxam;

    /** 入帳銀行別 */
    private String bnkCod;

    /** 入帳帳號 */
    private String acno;

    /** 入帳或匯款金額 */
    private String amt;

    /** 摘要 */
    private String memo;

    /** 附註 */
    private String rmk;

    /** 收款人姓名 */
    private String rcvName;

    /** 網銀帳務日 */
    private String ebActDate;

    /** 交易來源 */
    private String txSrc;

    /** 特店代號 */
    private String vnmrcd;

    /** CTI表單號碼 */
    private String ctiNo;

    /** 查詢起日 */
    private Calendar strDate;

    /** 查詢迄日 */
    private Calendar endDate;

    /** 退匯通知註記 */
    private String txSts;

    /** 空白 */
    private String filler;

    /** 匯費 */
    private String rtFee;

    /** 證件類型 */
    private String idType;

    /** Card_Borrow_cash_Log */
    private insertCardBorrowCashRequest log;

    /**
     * @return the funcCod
     */
    public String getFuncCod() {
        return funcCod;
    }

    /**
     * @param funcCod
     *            the funcCod to set
     */
    public void setFuncCod(String funcCod) {
        this.funcCod = funcCod;
    }

    /**
     * @return the seqNo
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * @param seqNo
     *            the seqNo to set
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the idno
     */
    public String getIdno() {
        return idno;
    }

    /**
     * @param idno
     *            the idno to set
     */
    public void setIdno(String idno) {
        this.idno = idno;
    }

    /**
     * @return the vncdno
     */
    public String getVncdno() {
        return vncdno;
    }

    /**
     * @param vncdno
     *            the vncdno to set
     */
    public void setVncdno(String vncdno) {
        this.vncdno = vncdno;
    }

    /**
     * @return the vendym
     */
    public String getVendym() {
        return vendym;
    }

    /**
     * @param vendym
     *            the vendym to set
     */
    public void setVendym(String vendym) {
        this.vendym = vendym;
    }

    /**
     * @return the vncvv2
     */
    public String getVncvv2() {
        return vncvv2;
    }

    /**
     * @param vncvv2
     *            the vncvv2 to set
     */
    public void setVncvv2(String vncvv2) {
        this.vncvv2 = vncvv2;
    }

    /**
     * @return the vntxam
     */
    public String getVntxam() {
        return vntxam;
    }

    /**
     * @param vntxam
     *            the vntxam to set
     */
    public void setVntxam(String vntxam) {
        this.vntxam = vntxam;
    }

    /**
     * @return the bnkCod
     */
    public String getBnkCod() {
        return bnkCod;
    }

    /**
     * @param bnkCod
     *            the bnkCod to set
     */
    public void setBnkCod(String bnkCod) {
        this.bnkCod = bnkCod;
    }

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

    /**
     * @return the amt
     */
    public String getAmt() {
        return amt;
    }

    /**
     * @param amt
     *            the amt to set
     */
    public void setAmt(String amt) {
        this.amt = amt;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the rmk
     */
    public String getRmk() {
        return rmk;
    }

    /**
     * @param rmk
     *            the rmk to set
     */
    public void setRmk(String rmk) {
        this.rmk = rmk;
    }

    /**
     * @return the rcvName
     */
    public String getRcvName() {
        return rcvName;
    }

    /**
     * @param rcvName
     *            the rcvName to set
     */
    public void setRcvName(String rcvName) {
        this.rcvName = rcvName;
    }

    /**
     * @return the ebActDate
     */
    public String getEbActDate() {
        return ebActDate;
    }

    /**
     * @param ebActDate
     *            the ebActDate to set
     */
    public void setEbActDate(String ebActDate) {
        this.ebActDate = ebActDate;
    }

    /**
     * @return the txSrc
     */
    public String getTxSrc() {
        return txSrc;
    }

    /**
     * @param txSrc
     *            the txSrc to set
     */
    public void setTxSrc(String txSrc) {
        this.txSrc = txSrc;
    }

    /**
     * @return the vnmrcd
     */
    public String getVnmrcd() {
        return vnmrcd;
    }

    /**
     * @param vnmrcd
     *            the vnmrcd to set
     */
    public void setVnmrcd(String vnmrcd) {
        this.vnmrcd = vnmrcd;
    }

    /**
     * @return the ctiNo
     */
    public String getCtiNo() {
        return ctiNo;
    }

    /**
     * @param ctiNo
     *            the ctiNo to set
     */
    public void setCtiNo(String ctiNo) {
        this.ctiNo = ctiNo;
    }

    /**
     * @return the strDate
     */
    public Calendar getStrDate() {
        return strDate;
    }

    /**
     * @param strDate
     *            the strDate to set
     */
    public void setStrDate(Calendar strDate) {
        this.strDate = strDate;
    }

    /**
     * @return the endDate
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the txSts
     */
    public String getTxSts() {
        return txSts;
    }

    /**
     * @param txSts
     *            the txSts to set
     */
    public void setTxSts(String txSts) {
        this.txSts = txSts;
    }

    /**
     * @return the filler
     */
    public String getFiller() {
        return filler;
    }

    /**
     * @param filler
     *            the filler to set
     */
    public void setFiller(String filler) {
        this.filler = filler;
    }

    /**
     * @return the rtFee
     */
    public String getRtFee() {
        return rtFee;
    }

    /**
     * @param rtFee
     *            the rtFee to set
     */
    public void setRtFee(String rtFee) {
        this.rtFee = rtFee;
    }

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType
     *            the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the log
     */
    public insertCardBorrowCashRequest getLog() {
        return log;
    }

    /**
     * @param log
     *            the log to set
     */
    public void setLog(insertCardBorrowCashRequest log) {
        this.log = log;
    }

}
