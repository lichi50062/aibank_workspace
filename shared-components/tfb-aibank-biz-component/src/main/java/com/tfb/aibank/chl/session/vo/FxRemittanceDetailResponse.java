package com.tfb.aibank.chl.session.vo;

import java.util.ArrayList;
import java.util.List;

//@formatter:off
/**
 * <p>
 * Description: 外幣匯入款查詢明細
 * </p>
 *
 * <p>
 * Modify History:
 * </p>
 * v1.0, 2023/09/12, Aaron
 * <ol>
 * <li>初版</li>
 * </ol>
 */

//@formatter:on
public class FxRemittanceDetailResponse {

    /** 交易編號. */
    private String trnNo;

    /** SWIFT輸出序號. */
    private String osn;

    /** 受益人. */
    private String beneficiary;

    /** 受益人沒trim過的資料 */
    private String beneContent;

    /** 匯出行. */
    private String orderingBank;

    /** 生效日 yyyy/MM/dd. */
    private String valueDate;

    /** 匯入幣別. */
    private String currency;

    /** 匯入金額 小數3位，最後一碼為正負號. */
    private String amt;

    /** 電文接收日. */
    private String ir05Date;

    /** 匯款人 */
    private List<String> orderintCust = new ArrayList<>();

    /** 匯款. */
    private String remittance;

    /** MP2匯入匯款交易編號. */
    private String irno;

    /** 場次. */
    private String item;

    /** 資料. */
    private String data;

    /** The pay not. */
    private String payNot;

    /** The msg seq not. */
    private String msgSeqNot;

    /** The busnnot. */
    private String busnnot;

    /** MsgStatusT RELS (自動入帳完成), PENT (已解款), TBPR (待解款). */
    private String msgStatust;

    /** The index no. */
    private String indexNo;

    /** The pageno. */
    private String pageno;

    /** The bf flag. */
    private String bfFlag;

    /** The tag 21. */
    private String tag21;

    /**
     * @return the trnNo
     */
    public String getTrnNo() {
        return trnNo;
    }

    /**
     * @param trnNo
     *            the trnNo to set
     */
    public void setTrnNo(String trnNo) {
        this.trnNo = trnNo;
    }

    /**
     * @return the osn
     */
    public String getOsn() {
        return osn;
    }

    /**
     * @param osn
     *            the osn to set
     */
    public void setOsn(String osn) {
        this.osn = osn;
    }

    /**
     * @return the beneficiary
     */
    public String getBeneficiary() {
        return beneficiary;
    }

    /**
     * @param beneficiary
     *            the beneficiary to set
     */
    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    /**
     * @return the orderingBank
     */
    public String getOrderingBank() {
        return orderingBank;
    }

    /**
     * @param orderingBank
     *            the orderingBank to set
     */
    public void setOrderingBank(String orderingBank) {
        this.orderingBank = orderingBank;
    }

    /**
     * @return the valueDate
     */
    public String getValueDate() {
        return valueDate;
    }

    /**
     * @param valueDate
     *            the valueDate to set
     */
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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
     * @return the ir05Date
     */
    public String getIr05Date() {
        return ir05Date;
    }

    /**
     * @param ir05Date
     *            the ir05Date to set
     */
    public void setIr05Date(String ir05Date) {
        this.ir05Date = ir05Date;
    }

    /**
     * @return the orderintCust
     */
    public List<String> getOrderintCust() {
        return orderintCust;
    }

    /**
     * @param orderintCust
     *            the orderintCust to set
     */
    public void setOrderintCust(List<String> orderintCust) {
        this.orderintCust = orderintCust;
    }

    /**
     * @return the remittance
     */
    public String getRemittance() {
        return remittance;
    }

    /**
     * @param remittance
     *            the remittance to set
     */
    public void setRemittance(String remittance) {
        this.remittance = remittance;
    }

    /**
     * @return the irno
     */
    public String getIrno() {
        return irno;
    }

    /**
     * @param irno
     *            the irno to set
     */
    public void setIrno(String irno) {
        this.irno = irno;
    }

    /**
     * @return the item
     */
    public String getItem() {
        return item;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the payNot
     */
    public String getPayNot() {
        return payNot;
    }

    /**
     * @param payNot
     *            the payNot to set
     */
    public void setPayNot(String payNot) {
        this.payNot = payNot;
    }

    /**
     * @return the msgSeqNot
     */
    public String getMsgSeqNot() {
        return msgSeqNot;
    }

    /**
     * @param msgSeqNot
     *            the msgSeqNot to set
     */
    public void setMsgSeqNot(String msgSeqNot) {
        this.msgSeqNot = msgSeqNot;
    }

    /**
     * @return the busnnot
     */
    public String getBusnnot() {
        return busnnot;
    }

    /**
     * @param busnnot
     *            the busnnot to set
     */
    public void setBusnnot(String busnnot) {
        this.busnnot = busnnot;
    }

    /**
     * @return the msgStatust
     */
    public String getMsgStatust() {
        return msgStatust;
    }

    /**
     * @param msgStatust
     *            the msgStatust to set
     */
    public void setMsgStatust(String msgStatust) {
        this.msgStatust = msgStatust;
    }

    /**
     * @return the indexNo
     */
    public String getIndexNo() {
        return indexNo;
    }

    /**
     * @param indexNo
     *            the indexNo to set
     */
    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    /**
     * @return the pageno
     */
    public String getPageno() {
        return pageno;
    }

    /**
     * @param pageno
     *            the pageno to set
     */
    public void setPageno(String pageno) {
        this.pageno = pageno;
    }

    /**
     * @return the bfFlag
     */
    public String getBfFlag() {
        return bfFlag;
    }

    /**
     * @param bfFlag
     *            the bfFlag to set
     */
    public void setBfFlag(String bfFlag) {
        this.bfFlag = bfFlag;
    }

    /**
     * @return the tag21
     */
    public String getTag21() {
        return tag21;
    }

    /**
     * @param tag21
     *            the tag21 to set
     */
    public void setTag21(String tag21) {
        this.tag21 = tag21;
    }

    /**
     * @return the beneContent
     */
    public String getBeneContent() {
        return beneContent;
    }

    /**
     * @param beneContent
     *            the beneContent to set
     */
    public void setBeneContent(String beneContent) {
        this.beneContent = beneContent;
    }

}
